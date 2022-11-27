function header() {
    return 'Bearer ' + localStorage.getItem("jwt");
}

 async function AddProcedureAndPostPhoto(){
    await CopyFile();
  await  AddProcedure();
 }


 async function GetProcedures()
 {
     let root=document.getElementById("procedures-list");
     const options ={
         method:'GET',
         headers:{
             // 'Content-Type': 'application/json',
             // 'Accept': 'application/json',
             'Authorization': header(),
         }}
     const data = await fetch("/procedures",options).then(res=>res.json()).then(
         res=>
         {
             let html = 'Not Found';
             if (!res.error)
             {
                  html = '<table style="width: 100%;\n' +
                      '    margin-bottom: 20px;\n' +
                      '    border: 5px solid #fff;\n' +
                      '    border-top: 5px solid #fff;\n' +
                      '    border-bottom: 3px solid #fff;\n' +
                      '    border-collapse: collapse;\n' +
                      '    outline: 3px solid #ffd300;\n' +
                      '    font-size: 15px;\n' +
                      '    background: #fff!important;">' +
                      '    <thead>' +
                      '    <tr>' +
                      '        <th>Name</th>' +
                      '        <th>Type</th>' +
                      '        <th>Age</th>' +
                      '    </tr>' +
                      '    </thead>' +
                      '    <tbody>';
                  console.log('111 res: ', res)
                  res.forEach(obj => {
                      let imgSrc = `/images/${obj.photo}`
                      html += '<tr>' +
                          '<td>' + obj.nameProcedure+ '</td>' +
                          '<td>' + obj.price + '</td>' +
                          '<td><img src="' + imgSrc + '"/ style="width:50px"> </td>' +
                          '</tr>';
                  });
                  html += '</tbody></table>'
             }

             root.innerHTML = html;
         }
     );
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
    const resul = await result.json();
}


