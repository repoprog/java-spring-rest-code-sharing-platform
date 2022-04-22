<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
</head>
<link rel="stylesheet"
       href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
<script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
<link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<ul>
<#list codes as snippet>
<li>
<span id="load_date" class="time">${snippet.date}</span>
<br>
<pre id="code_snippet" class="code"><code>${snippet.code}</code></pre>
</li>
</#list>
</ul>
</body>
</html>