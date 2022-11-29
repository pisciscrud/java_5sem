class NoteCard {

    constructor({time,date,statusName,masterName, procedureName,id,petNickname},id_role) {
      this.statusName=statusName
      this.masterName=masterName
      this.procedureName=procedureName
      this.petNickname=petNickname
      this.time=time
      this.date=date
      this.id=id
        this.id_role=id_role;
    }

    render() {
        let adminHTML='';
        if(this.id_role == 1)
        {
            adminHTML+=`<div class="note-card-admin">
               <input type="button" value="+" onclick="setStatus(${this.id},1)"></input>              
                <input type="button" value="x" onclick="setStatus(${this.id},3)"></input>              
                </div>`
        }

        return `
            <div class="note-card">
                <div class="note-card-body">
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
         ${adminHTML} 
            </div>
            
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