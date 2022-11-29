class NoteCardAAdmin {

    constructor({time,date,statusName,masterName, procedureName,id,petNickname,ownerLogin}) {
        this.ownerLogin=ownerLogin
        this.statusName=statusName
        this.masterName=masterName
        this.procedureName=procedureName
        this.petNickname=petNickname
        this.time=time
        this.date=date
        this.id=id
    }

    render() {
        return `
            <a href="/procedure.html?id=${this.id}" class="note-card">
                <div class="note-card-body">
                    <span>${this.ownerLogin}</span>
                    <span class="note-card-nameProcedure">${this.procedureName}</span>
                    <span class="note-card-nameMaster">Master: ${this.masterName}</span>
                    <span class="note-card-petNickname">Pet: ${this.petNickname}</span>
                </div>
                <p class="note-card-statusName">
                    <span>Status: </span><span class="status-label ${this.getStatusColor(this.statusName)}">${this.statusName}</span>
                </p>
                <div class="note-card-footer">
                    <span class="note-card-date">${this.date}</span>
                    <span class="note-card-time">${this.time}</span>
                </div> 
                <input type="button" value="deny" onclick="setStatus(3)"></input>              
                <input type="button" value="approve" onclick="setStatus(1)"></input>              
            </a>
        `
    }

    getStatusColor(status) {
        switch (status) {
            case 'Waiting': {
                return 'waiting'
            }
            case 'Denied': {
                return 'denied'
            }
            case 'Approved': {
                return 'approved'
            }
            default: {
                return 'waiting'
            }
        }
    }
}