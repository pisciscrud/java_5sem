

function errorPage(mes){return `<div><h3>${mes}</h3><h4><a href="/login">Pls, sing in</a></h4></div>`;
}
async function singOut(){
    localStorage.setItem("jwt", "");
    document.location.href = "/login";
}
function header(){return 'Bearer ' + localStorage.getItem("jwt");}
window.addEventListener('DOMContentLoaded', e => {
    // paginationPage = getURLParameter(location.search, 'page') || 0
    // console.log('111 paginationPage', paginationPage)
    PetsOfUser()// root function
})
async function AddPet() {
    let nickname=document.getElementById("nickname").value;
    let age = document.getElementById("age").value;
    let pet_type_id = document.getElementById("pet_type_id").value;
    fetch("/pets/AddPet", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization' : header()},
        body: JSON.stringify({age, pet_type_id, nickname})
}).then(res => res.json()).then(res => {
        if(res.status == 400){
            $('#mesP').text("Enter correct data");
        }else {
            if(res.status == 500){
                $('#div').html(errorPage("Authorisation Error"));
            }else {
                if (res.error) {
                    $('#mesP').text(res.error);
                } else {
                    $('#mesP').text("Successful");
                    PetsOfUser();
                }
            }
        }
    }
    );
}
async function PetsOfUser() {
    document.getElementById("listP").innerHTML = "";
    await fetch("/pets/PetForUser", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': header()
        }}).then(res => res.json()).then(res => {
        let str = 'Not Found';

        if(res.status == 400){
            $('#mesP').text("Enter correct data");
        }else {
            if (res.status == 500) {
                $('#div').html(errorPage("Authorisation Error"));
            } else {
                if (!res.error) {

                    str = '<table style="width: 100%;\n' +
                        '    margin-bottom: 20px;\n' +
                        '    border: 5px solid #fff;\n' +
                        '    border-top: 5px solid #fff;\n' +
                        '    border-bottom: 3px solid #fff;\n' +
                        '    border-collapse: collapse;\n' +
                        '    outline: 3px solid #ffd300;\n' +
                        '    font-size: 20px;\n' +
                        '    background: #fff!important;">' +
                        '    <thead>' +
                        '    <tr>' +
                        '        <th>Name</th>' +
                        '        <th>Type</th>' +
                        '        <th>Age</th>' +
                        '    </tr>' +
                        '    </thead>' +
                        '    <tbody>';
                    res.forEach(obj => {
                        str += '<tr>' +
                            '<td>' + obj.nickname + '</td>' +
                            '<td>' + obj.petName + '</td>' +
                            '<td>' + obj.age + '</td>' +
                            '<td><input type="button" value="X" onclick="deletePet(' + obj.id + ')"></input> </td>' +
                            '</tr>';
                    });
                    str += '</tbody></table>'
                }
                document.getElementById("listP").innerHTML = str;

            }

        }
        });

    }
    async function deletePet(id) {
        let del = confirm("Delete?");
        if (del == false)
            return;
        await fetch("/pets/DeletePet/" + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': header()
            }}).then(res => res.json()).then(res => {
            if(res.status == 400){
                $('#mesP').text("Enter correct data");
            }else {
                if(res.status == 500){
                    $('#div').html(errorPage("Authorisation Error"));
                } else {
                       PetsOfUser();
                    }

            }
        });
    }


