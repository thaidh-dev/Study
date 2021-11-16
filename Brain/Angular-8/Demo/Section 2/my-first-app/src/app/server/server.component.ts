import {Component} from "@angular/core"

@Component({
    selector: 'app-server',
    templateUrl: '/server.component.html',
    styles: [`
        .online {
            color: pink;
        }
    `]
})
export class ServerComponent {
    serverId: number = 7;
    serverStatus: string = "offLinse";

    constructor() {
        this.serverStatus = Math.random() > 0.5 ? 'đang on line' : 'đang off line';
    }

    getServerStatus() {
        return this.serverStatus;
    }

    getColor() {
        return this.serverStatus  === 'đang on line' ? 'green' : 'red';

    }
}
