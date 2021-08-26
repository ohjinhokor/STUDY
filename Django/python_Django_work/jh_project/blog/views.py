from django.shortcuts import render, get_object_or_404, redirect
from .models import Blog
from django.utils import timezone
from .forms import BlogForm

def home(request):
    blogs = Blog.objects.all()
    return render(request, 'blogHome.html', {'blogs' : blogs})

def detail(request, blog_id):
    blog = get_object_or_404(Blog, pk=blog_id)
    return render(request, 'detail.html', {'blog':blog})

def new(request):
    form = BlogForm()
    return render(request, 'new.html', {'form': form})

def create(request):
    form = BlogForm(request.POST, request.FILES)
    if form.is_valid():
        new_blog = form.save(commit=False)
        new_blog.pub_date = timezone.now()
        new_blog.save()
        return redirect('blogDetail', new_blog.id)
    return redirect('blogHome')

def edit(request, edit_blog_id):
    edit_blog = Blog.objects.get(id=edit_blog_id)
    return render(request, 'edit.html', {'blog':edit_blog})

def update(request, updateid):
    update_blog = Blog.objects.get(id=updateid)
    update_blog.title = request.POST['title']
    update_blog.writer = request.POST['writer']
    update_blog.body = request.POST['body']
    update_blog.pub_date = timezone.now()
    update_blog.save()
    return redirect('blogDetail', update_blog.id)

def delete(request, id):
    delete_blog = Blog.objects.get(id = id)
    delete_blog.delete()
    return redirect('/blog/')