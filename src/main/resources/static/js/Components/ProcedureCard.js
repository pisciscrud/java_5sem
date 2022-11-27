export class ProcedureCard {
    title

    constructor(title) {
        this.title = title
    }

    render() {
        return `
            <div class="procedure-card">
                <h4>${this.title}</h4>
            </div>
        `
    }
}