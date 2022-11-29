function header(){return 'Bearer ' + localStorage.getItem("jwt");}
window.addEventListener('DOMContentLoaded', e => {
    // paginationPage = getURLParameter(location.search, 'page') || 0
    // console.log('111 paginationPage', paginationPage)
    // root function

    GetProcedures()
})

async function AddProcedureAndPostPhoto(){
    await CopyFile();
    await  AddProcedure();
}

async function CopyFile() {
    let file = document.getElementById("file").files[0];
    const formData = new FormData();
    formData.append("file", file);
    const options =
        {
            method: 'POST',
            headers: {
                'Authorization': header()
            },
            body: formData
        };
    const result = await fetch("/procedures/CopyFile",options);
}

 async function AddProcedure() {
     let nameProcedure = document.getElementById("name").value;
     let price = document.getElementById("price").value;
     let photo = document.getElementById("file").files[0].name;
     const result = await fetch("/procedures/add", {
         method: 'POST',
         headers: {
             'Content-Type': 'application/json',
             'Accept': 'application/json',
             'Authorization': header()
         },
         body: JSON.stringify({price, nameProcedure,   photo})
     });
     const res = await result.json();
        if(!res.error)
        {
            GetProcedures();
        }
 }

 async function GetProcedures(){
     document.getElementById("ListProcedures").innerHTML = "";
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': header()
            }
        }
        await fetch ("/procedures",options)
            .then(res=>res.json())
            .then(res=> {
                str = '<h2>Procedures</h2>' +
                    '<table style="width: 100%;\n' +
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
                    '        <th>Procedure</th>' +
                    '        <th>$</th>' +
                    '    </tr>' +
                    '    </thead>' +
                    '    <tbody>';
                res.forEach(obj => {
                    str += '<tr>' +

                        '<td>' + obj.nameProcedure + '</td>' +
                        '<td>' + obj.price + '</td>' +
                        '<td><input type="button" value="X" onclick="deleteProcedure(' + obj.id + ')"></input> </td>' +

                        '</tr>';
                });
                str += '</tbody></table>'

     document.getElementById("ListProcedures").innerHTML = str;


    });
}
async function deleteProcedure(id) {
    const options = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': header()
        }
    }
    await fetch ("/procedures/delete/" + id,options)
        .then(res=>res.json())
        .then(res=> {
            if(!res.error)
            GetProcedures();
        });
}