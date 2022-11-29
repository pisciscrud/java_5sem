class ProcedureCard {
    title
    price

    constructor({nameProcedure, price, photo,id}) {
        this.title = nameProcedure
        this.price = price
        this.photo = photo
        this.id=id
    }

    render() {
        let adminHTML='';
        if(this.id_role == 1)
        {
            adminHTML+=`<div class="note-card-admin">    
                <input type="button" value="x" onclick="setStatus(${this.id},3)"></input>              
                </div>`
        }
        return `
            <a href="/procedure.html?id=${this.id}" class="procedure-card">
                <img class="procedure-card-img" src="../images/${this.photo}" alt="${this.title}">
                <h4 class="procedure-card-title">${this.title}</h4>
                <span class="procedure-card-price">${this.price} BYN</span>
            </a>
        `
    }
}