<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
</head>
<link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<ul>
<#list codes as snippet>
<li>
<span id="load_date" class="time">${snippet.date}</span>
<br>
<pre id="code_snippet" class="code"><p>${snippet.code}</p></pre>
</li>
</#list>
</ul>
</body>
</html>