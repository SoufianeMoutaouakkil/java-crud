<h1>User Form</h1>
<form method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${param.name}">
    <br><br>
    
    <label for="email">Email:</label>
    <input type="text" id="email" name="email" value="${param.email}">
    <br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" value="${param.password}">
    <br><br>

    <input type="submit" value="Submit">
</form>
<p>${pageContext.request.getAttribute("error")}</p>