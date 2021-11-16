import { Component, OnInit, ViewChildren, QueryList } from '@angular/core';
import { NhanVien } from './common/nhan-vien';
import { HttpClient } from '@angular/common/http';
import { NgbdSortableHeader, SortEvent, compare } from './sortable.directive';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  lstNhanVien: NhanVien[];
  lstNhanVienNguyenGoc: NhanVien[];

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.getListNhanVien();
  }

  getListNhanVien(): void {
    this.httpClient.get<NhanVien[]>("http://localhost:8080/TrangChu/NhanVien").subscribe(data => {
      this.lstNhanVien = data;
      this.lstNhanVienNguyenGoc = data;
    });
  }
}

