<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
<link rel="stylesheet" href="/css/style.css"></head>
<body>

<span id="load_date" class="time">${codeInstance.date}</span>
<br>
<#if codeInstance.restricted == "none">
<#elseif codeInstance.restricted == "time">
 The code will be available for <span id="time_restriction"><b><u>${codeInstance.time}</u></b></span> seconds.
 <br>
<#elseif  codeInstance.restricted == "view" >
<span id="views_restriction"><b><u>${codeInstance.views}</u></b></span> more views allowed.
<br>
<#elseif  codeInstance.restricted == "both">
<span id="views_restriction"><b><u>${codeInstance.views}</u></b></span> more views allowed.
<br>
 The code will be available for <span id="time_restriction"><b><u>${codeInstance.time}</u></b></span> seconds.
 <br>
</#if>

<pre id="code_snippet" class="code"><code>${codeInstance.code}</code></pre>
</body>
</html>