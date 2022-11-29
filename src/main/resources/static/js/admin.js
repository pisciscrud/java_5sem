function header(){return 'Bearer ' + localStorage.getItem("jwt");}
let paginationPage = 0;
let maxPaginationPage = 0;
const rowsPerPage = 4;
const id_role=localStorage.getItem("role");
const root = document.getElementById("application-list");
const [ prevPageBtn, nextPageBtn ] = document.querySelectorAll('.pagination-button')
let applicationsData=[];
window.addEventListener('DOMContentLoaded', e => {
    paginationPage = getURLParameter(location.search, 'page') || 0
    //console.log('111 paginationPage', paginationPage)
    GetApplications()// root function
})
function header() {
    return 'Bearer ' + localStorage.getItem("jwt");
}
function getURLParameter(url, param) {
    const searchParams = new URLSearchParams(url);
    return searchParams.get(param)
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
//AddMaster

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
                        option.value=obj.id;
                        select.append(option);
                    })
                }
            }
        )
}
async function GetMasters() {
    document.getElementById("ListMasters").innerHTML = "";
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
async function setStatus(id,status_id){
    const options={
        method:'Post',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': header()
        },
        body:JSON.stringify({status_id})
    }
    await fetch("/schedule/set-status/"+id,options)
        .then(res => res.json()).then(res => {
            if (!res.error) {
            GetApplications();
            }

        })
}

async function GetApplications(){
    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': header()
        }
    }
    await fetch("/schedule/applications",options)
        .then(res=>res.json())
        .then(res => {
            cardsData = res
            maxPaginationPage = Math.ceil(cardsData.length / rowsPerPage) - 1
            const cardsDataForPage = prepareCardsData(cardsData);
            renderPage(cardsDataForPage)

        });

}
function prepareCardsData(data) {
    const start = paginationPage * rowsPerPage
    const end = start + rowsPerPage
    return data.slice(start, end)
}
function renderPage(applicationsData)
{
    let tableHtml = ''
    if(!applicationsData.error)
    {
        console.log('111 data: ', applicationsData)
        applicationsData.forEach(applicationsData=>{
            tableHtml += new NoteCard(applicationsData,id_role).render()
        });

    } else {
        tableHtml = 'error...'
    }
    root.innerHTML = tableHtml;
}
prevPageBtn.addEventListener('click', () => {
    paginationPage -= 1;
    if (paginationPage < 0) {
        paginationPage = 0;
    }
    setPaginationAccess()
    console.log('openPrevPage', paginationPage)
    const cardsDataForPage = prepareCardsData(tableData);
    renderPage(cardsDataForPage)
})

nextPageBtn.addEventListener('click', () => {
    paginationPage += 1;
    if (paginationPage >= maxPaginationPage) {
        paginationPage = maxPaginationPage
    }
    setPaginationAccess()
    console.log('openNextPage', paginationPage)
    const cardsDataForPage = prepareCardsData(tableData);
    renderPage(cardsDataForPage)
})

function setPaginationAccess() {
    prevPageBtn.disabled = false;
    nextPageBtn.disabled = false
    if (paginationPage === maxPaginationPage) {
        nextPageBtn.disabled = true
    }
    if (paginationPage === 0) {
        prevPageBtn.disabled = true
    }
}




