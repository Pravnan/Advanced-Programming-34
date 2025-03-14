
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Admin</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                color: #333;
                margin: 0;
                padding: 0;
            }

            h1 {
                text-align: center;
                margin-top: 30px;
                color: #5a5a5a;
            }

            .container {
                max-width: 750px;
                margin: 30px auto;
                background-color: #fff;
                padding: 40px;
                border-radius: 8px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            }

            form {
                display: flex;
                flex-direction: column;
                gap: 20px;
            }

            label {
                font-weight: bold;
                margin-bottom: 5px;
                color: #555;
            }

            input[type="text"],
            input[type="password"],
            select {
                padding: 12px 15px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 16px;
                width: 100%;
                box-sizing: border-box;
                transition: border-color 0.3s ease, box-shadow 0.3s ease;
            }

            input[type="text"]:focus,
            input[type="password"]:focus,
            select:focus {
                border-color: #4CAF50;
                box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
                outline: none;
            }


            button[type="submit"] {
                padding: 14px 20px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                font-size: 16px;
                cursor: pointer;
                border-radius: 4px;
                transition: background-color 0.3s ease;
            }

            button[type="submit"]:hover {
                background-color: #45a049;
            }

            .back-button {
                display: inline-block;
                margin-top: 20px;
                padding: 12px 24px;
                background-color: #ccc;
                color: #333;
                text-decoration: none;
                border-radius: 4px;
                font-size: 16px;
                transition: background-color 0.3s ease;
            }

            .back-button:hover {
                background-color: #bbb;
            }
            */

            @media (max-width: 768px) {
                .container {
                    padding: 20px;
                }

                input[type="text"],
                input[type="password"],
                select {
                    font-size: 14px;
                }
            }
        </style>
</head>
<body>
    <h1>Edit Admin</h1>

    <div class="container">
        <c:if test="${not empty admin}">
            <form action="admin?action=update" method="post">
                <input type="hidden" name="adminID" value="${admin.adminID}">

                <div>
                    <label>Username:</label>
                    <input type="text" name="username" value="${admin.username}" required>
                </div>

                <div>
                    <label>Password:</label>
                    <input type="password" name="passwordHash" value="${admin.passwordHash}" required>
                </div>

                <div>
                    <label>Role:</label>
                    <select name="role">
                        <option value="Admin" ${admin.role == 'Admin' ? 'selected' : ''}>Admin</option>
                        <option value="Manager" ${admin.role == 'Manager' ? 'selected' : ''}>Manager</option>
                    </select>
                </div>


                <button type="submit">Update Admin</button>
            </form>
        </c:if>


        <a class="back-button" href="admin?action=list">Back to Admin List</a>
    </div>
</body>
</html>