async function LoginMe(e) {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let resultMessage = document.getElementById("resultMessage");
    try {
        const user = await fetch("/users/login", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({login, password})
        });
        const data = await user.json();
        console.log(data);
        if(data==null){
            $('#error').text("Sorry uncorrect login or password");
        }
        localStorage.setItem("jwt", data.token);
        localStorage.setItem("role",data.role_id);
        if (data.role_id == 1) {
            document.location.href = "/admin";
               }
                else if (data.role_id == 2) {
                    document.location.href = "/";
            }
                else {
            $('#error').text("Sorry uncorrect login or password");
        }
    } catch (e) {
        alert(e);
    }
}