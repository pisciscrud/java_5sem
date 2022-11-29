//function header(){return 'Bearer ' + localStorage.getItem("jwt");}
let paginationPage = 0;
let maxPaginationPage = 0;
const rowsPerPage = 4;
let tableData = [];
const root = document.getElementById("history-list");
const [ prevPageBtn, nextPageBtn ] = document.querySelectorAll('.pagination-button')
window.addEventListener('DOMContentLoaded', e => {
    paginationPage = getURLParameter(location.search, 'page') || 0
    console.log('111 paginationPage', paginationPage)
    GetHistory() // root function
})
function header() {
    return 'Bearer ' + localStorage.getItem("jwt");
}
function getURLParameter(url, param) {
    const searchParams = new URLSearchParams(url);
    return searchParams.get(param)
}
async function GetHistory() {
   // document.getElementById("ListH").innerHTML = "";
    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': header()
        },
    }
    const result = fetch("/schedule/current", options).then(res => res.json())
        .then(res => {
            if (!res.error)
                tableData = res
            maxPaginationPage = Math.ceil(tableData.length / rowsPerPage) - 1
            const cardsDataForPage = prepareCardsData(tableData);
            renderPage(cardsDataForPage)
        });
}

            // str = '<table style="width: 100%;\n' +
            //     '    margin-bottom: 20px;\n' +
            //     '    border: 5px solid #fff;\n' +
            //     '    border-top: 5px solid #fff;\n' +
            //     '    border-bottom: 3px solid #fff;\n' +
            //     '    border-collapse: collapse;\n' +
            //     '    outline: 3px solid #ffd300;\n' +
            //     '    font-size: 20px;\n' +
            //     '    background: #fff!important;">' +
            //     '    <thead>' +
            //     '    <tr>' +
            //     '        <th>Name</th>' +
            //     '        <th>Type</th>' +
            //     '        <th>Age</th>' +
            //     '    </tr>' +
            //     '    </thead>' +
            //     '    <tbody>';
            // res.forEach(obj => {
            //     str += '<tr>' +
            //         '<td>' + obj.nickname + '</td>' +
            //         '<td>' + obj.petName + '</td>' +
            //         '<td>' + obj.age + '</td>' +
            //         '<td><input type="button" value="X" onclick="deletePet(' + obj.id + ')"></input> </td>' +
            //         '</tr>';
            // });
            // str += '</tbody></table>'

function prepareCardsData(data) {
    const start = paginationPage * rowsPerPage
    const end = start + rowsPerPage
    return data.slice(start, end)
}
  function renderPage(tablesData)
  {
      let tableHtml = ''
      if(!tablesData.error)
      {
          console.log('111 data: ', tablesData)
          tablesData.forEach(tableData=>{
              tableHtml += new NoteCard(tableData).render()
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

