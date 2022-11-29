let paginationPage = 0;
let maxPaginationPage = 0;
const cardsPerPage = 5;
let cardsData = [];
const root = document.getElementById("procedures-list");
const [ prevPageBtn, nextPageBtn ] = document.querySelectorAll('.pagination-button')

window.addEventListener('DOMContentLoaded', e => {
    paginationPage = getURLParameter(location.search, 'page') || 0
    //console.log('111 paginationPage', paginationPage)
    // setPaginationAccess()
    GetProcedures() // root function
})


async function SortAllProceduresByPrice(id)
{
    const options ={
        method:'GET',
        headers:{
            // 'Content-Type': 'application/json',
            // 'Accept': 'application/json',
            'Authorization': header(),
        }}
    await fetch("/procedures/sort/"+id,options)
        .then(res=>res.json())
        .then(res => {
            cardsData = res
            maxPaginationPage = Math.ceil(cardsData.length / cardsPerPage) - 1
            const cardsDataForPage = prepareCardsData(cardsData);
            renderPage(cardsDataForPage)
        });

}

function getURLParameter(url, param) {
    const searchParams = new URLSearchParams(url);
    return searchParams.get(param)
}

function header() {
    return 'Bearer ' + localStorage.getItem("jwt");
}




 async function GetProcedures()
 {
     const options ={
         method:'GET',
         headers:{
             // 'Content-Type': 'application/json',
             // 'Accept': 'application/json',
             'Authorization': header(),
         }}
     await fetch("/procedures",options)
         .then(res=>res.json())
         .then(res => {
             cardsData = res
             maxPaginationPage = Math.ceil(cardsData.length / cardsPerPage) - 1
             const cardsDataForPage = prepareCardsData(cardsData);
             renderPage(cardsDataForPage)
         });
 }

 function prepareCardsData(data) {
     const start = paginationPage * cardsPerPage
     const end = start + cardsPerPage
     return data.slice(start, end)
 }

 function renderPage(cardsData) {
     let proceduresHtml = ''
     if (!cardsData.error) {
         console.log('111 data: ', cardsData)
         cardsData.forEach(cardData => {
             proceduresHtml += new ProcedureCard(cardData).render()
         });
     } else {
         proceduresHtml = 'error...'
     }
     root.innerHTML = proceduresHtml;
 }




prevPageBtn.addEventListener('click', () => {
    paginationPage -= 1;
    if (paginationPage < 0) {
        paginationPage = 0;
    }
    setPaginationAccess()
    console.log('openPrevPage', paginationPage)
    const cardsDataForPage = prepareCardsData(cardsData);
    renderPage(cardsDataForPage)
})

nextPageBtn.addEventListener('click', () => {
    paginationPage += 1;
    if (paginationPage >= maxPaginationPage) {
        paginationPage = maxPaginationPage
    }
    setPaginationAccess()
    console.log('openNextPage', paginationPage)
    const cardsDataForPage = prepareCardsData(cardsData);
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