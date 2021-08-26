from django.contrib import admin
from django.urls import path
from .views import *

urlpatterns = [

    path('', home, name="blogHome"),
    path('new/', new, name="blogNew"),
    path('<str:blog_id>', detail, name="blogDetail"),
    path('create/', create, name="blogCreate"),
    path('edit/<str:edit_blog_id>', edit, name = "blogEdit"),
    path('update/<str:updateid>', update, name= "blogUpdate"),
    path('delete/<str:id>', delete, name = "blogDelete"),
]