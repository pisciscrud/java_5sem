function header(){return 'Bearer ' + localStorage.getItem("jwt");}
window.addEventListener('DOMContentLoaded', e => {
    // paginationPage = getURLParameter(location.search, 'page') || 0
    // console.log('111 paginationPage', paginationPage)
    GetProcedures()// root function
    GetMasters()
})



async function GetProcedures(){
    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': header()
        }
    }
  const select =  document.getElementById("nameProcedure");
      select.innerHTML="";
    await fetch("/procedures",options)
        .then(res=>res.json())
        .then(res=> {
                let str = 'Not Found';
           if (!res.error)
           {
               res.forEach(obj=>{console.log(obj)
               const option =document.createElement("option")
                   option.innerHTML=obj.nameProcedure;
                   option.value=obj.nameProcedure;
                   select.append(option);
               })
           }
            }
        )
}

async function GetMasters() {
    document.getElementById("ListM").innerHTML = "";
    await fetch("/masters ",{
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
                    const select =  document.getElementById("nameMaster");
                    select.innerHTML="";
                    res.forEach(obj=>{console.log(obj)
                        const option =document.createElement("option")
                        option.innerHTML=obj.nameMaster;
                        option.value=obj.nameMaster;
                        select.append(option);
                    })
                    str = '<h2>Masters</h2>'+
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
                        '        <th>Name</th>' +
                        '        <th>Procedure</th>' +
                        '        <th>$</th>' +
                        '    </tr>' +
                        '    </thead>' +
                        '    <tbody>';
                    res.forEach(obj => {
                        str += '<tr>' +
                            '<td>' + obj.nameMaster + '</td>' +
                            '<td>' + obj.nameProcedure + '</td>' +
                            '<td>' + obj.priceProcedure + '</td>' +
                            '</tr>';
                    });
                    str += '</tbody></table>'
                }
                document.getElementById("ListM").innerHTML = str;
            }
        }
    });

}
async function AddNote(){
    let nickname=document.getElementById("petNickname").value;
    let procedureName = document.getElementById("nameProcedure").value;
    let masterName = document.getElementById("nameMaster").value;
    let date = document.getElementById("date").value;
    let time = document.getElementById("time").value;

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': header()
        },
        body: JSON.stringify({nickname,date,time,masterName,procedureName})
    }
    await fetch("/schedule/Add",options)
        .then(
            res=>res.json())
        .then(res=> {
            console.log(res);
                let str = 'Not Found';
                if(res.error){
                    $('#mesP').text("Sorry in this time master is busy");
                }

                    else {
                        if (!res.error) {
                            $('#mesP').text("Added");
                        }
                    }
                }
        )
}