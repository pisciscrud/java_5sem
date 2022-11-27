function header(){return 'Bearer ' + localStorage.getItem("jwt");}


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