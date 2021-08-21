var http = require('http');
var fs = require('fs');
var url = require('url');
var qs = require('querystring');

/*var template = {
  html : function(title, list, body, control){

    var template = `
    <!doctype html>
    <html>
    <head>
      <title>WEB1 - ${title}</title>
      <meta charset="utf-8">
    </head>
    <body>
      <h1><a href="/">WEB</a></h1>
      ${list}
      ${control}
      ${body}
    </body>
    </html>
        `
        return template;
  },
  list : function(filelist){
    var list = '<ul>';
    var i =0;
    while(i<filelist.length){
      list = list+`<li><a href="/?id=${filelist[i]}">${filelist[i]}</a></li>`;
      i +=1;
    }
    list = list + '</ul>';
    return list;
  }
}
*/


function templateHTML(title, list, body, control){

  var template = `
  <!doctype html>
  <html>
  <head>
    <title>WEB1 - ${title}</title>
    <meta charset="utf-8">
  </head>
  <body>
    <h1><a href="/">WEB</a></h1>
    ${list}
    ${control}
    ${body}
  </body>
  </html>
      `
      return template;
}

function templateList(filelist){
  var list = '<ul>';
  var i =0;
  while(i<filelist.length){
    list = list+`<li><a href="/?id=${filelist[i]}">${filelist[i]}</a></li>`;
    i +=1;
  }
  list = list + '</ul>';
  return list;
}

var app = http.createServer(function(request,response){
    var _url = request.url;
    var QueryData = url.parse(_url, true).query;
    var pathname = url.parse(_url, true).pathname; // 포트 다음 query문 전 중간에 있는게 path 인듯하다.
    if(pathname==='/'){
        if(QueryData.id===undefined){
           var title = 'Welcome';
           var description = 'Hello, Node js';
           fs.readdir('./data', function(err,filelist){
                  var list = templateList(filelist);
                  var template = templateHTML(title,list,`<h2>${title}</h2>${description}`,
                  `<a href="/create">create</a>`)
                  response.writeHead(200);            
                  response.end(template);    
                })              
        } else {
            fs.readFile(`data/${QueryData.id}`, 'utf8', function(err, description){
            fs.readdir('./data/', function(error, filelist){
             // console.log(filelist);
              var title = QueryData.id;
              var list= templateList(filelist);
              var template = templateHTML(title, list, `<h2>${title}</h2>${description}`,
              `<a href="/create">create</a> 
               <a href="/update?id=${title}">update</a>
               <form action="delete_process" method="post">
                <input type="hidden" name="id" value="${title}">
                <input type="submit" value="delete">
               </form>
               `);
              //console.log(__dirname + _url);
              //console.log(url.parse(_url, true));
              response.writeHead(200);            
              response.end(template);   
      })
    });
  }
} else if(pathname==="/create"){
  var title = 'Welcome';
  var description = 'Hello, Node js';
  fs.readdir('./data', function(err,filelist){
         var list = templateList(filelist);
         var template = templateHTML(title,list,
          `<form action ="/create_process"
method="post">
<p><input type="text" name="title" placeholder="title을 입력하세요"></p>
<p>
    <textarea name="description" placeholder="description을 입력하세요"></textarea>
</p>
<p>
    <input type="submit">
</p>
</form>`, '');
         response.writeHead(200);            
         response.end(template);    
       }); 
} else if(pathname==="/create_process"){
    var body=''
    request.on('data', function(data){
      body = body+data;
    });
    request.on('end', function(){
      var post = qs.parse(body);
      var title = post.title;
      var description = post.description;
      fs.writeFile(`data/${title}`, description, 'utf8', function(err){
        response.writeHead(302, {Location: `/?id=${title}`});
        response.end();
      });
    });

} else if(pathname==="/update"){
    fs.readFile(`data/${QueryData.id}`, 'utf8', function(err, description){
      fs.readdir('./data/', function(error, filelist){
      // console.log(filelist);
        var title = QueryData.id;
        var list= templateList(filelist);
        var template = templateHTML(title, list, 
          `
          <form action ="/update_process"
          method="post">
          <input type="hidden" name="id" value="${title}">
          <p><input type="text" name="title" placeholder="title을 입력하세요" value="${title}"></p>
          <p>
              <textarea name="description" placeholder="description을 입력하세요">${description}</textarea>
          </p>
          <p>
              <input type="submit">
          </p>
          </form> `,
        `<a href="/create">create</a> <a href="/update?id=${title}">update</a>`);
        //console.log(__dirname + _url);
        //console.log(url.parse(_url, true));
        response.writeHead(200);            
        response.end(template);   
    })
  });
} else if(pathname==="/update_process"){
  var body=''
  request.on('data', function(data){
    body = body+data;
  });
  request.on('end', function(){
    var post = qs.parse(body);
    var id = post.id;
    var title = post.title;
    var description = post.description;
    fs.rename(`data/${id}`, `data/${title}`, function(error){
      fs.writeFile(`data/${title}`, description, 'utf8', function(err){
        response.writeHead(302, {Location: `/?id=${title}`});
        response.end();
      })
    })
  });
} else if(pathname==="/delete_process"){
  var body=''
  request.on('data', function(data){
    body = body+data;
  });
  request.on('end', function(){
    var post = qs.parse(body);
    var id = post.id;
    fs.unlink(`data/${id}`, function(error){
      response.writeHead(302, {Location: `/`});
      response.end();
    });
  });
} else {
        response.writeHead(404);
        response.end('Not Found');
    }
  });

app.listen(3000);