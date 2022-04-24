<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <script src="/actions.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
    <form>
        <textarea id="code_snippet" class="code-form"> //write your code here </textarea>
        Time restriction: <input id="time_restriction" type="text"/>
        <br>
        Views restriction: <input id="views_restriction" type="text"/>
        <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    </form>
    </body>
    </html>