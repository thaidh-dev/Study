import {Component, OnInit} from "@angular/core"
import { HttpClient } from '@angular/common/http';

@Component({
    selector: "app-nhanVien",
    templateUrl: "./nhanVien.component.html"
    // ./ tính từ angular/src/app/NhanVien, tính từ thư mục chứa nó
})
export class NhanVienComponent implements OnInit {
    nhanViens: any;

    constructor(private http: HttpClient) {

    }

    ngOnInit() {
        this.getAllNhanVien();
    }

    getAllNhanVien() {
        // this.http.get("/weatherforecast").subscribe(function(response) {

        // },
        // function(error) {

        // }
        // );

        // viết ngắn lại thành
        this.http.get("http://localhost:5000/weatherforecast").subscribe(
            response => {
                this.nhanViens = response;
            },
            error => {
                console.log(error);
            }
        );

    }
}
