async function Send() {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    fetch("/users/registration", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            fullName:name,
            login:login,
            password:password,
            email:email
        })
    }).then(res => res.json()).then(res => {
        let data = res;
        if (!data.error) {
            document.location.href = "/login";
        } else {
            document.getElementById("error").innerHTML = data.error;
        }
    });
}