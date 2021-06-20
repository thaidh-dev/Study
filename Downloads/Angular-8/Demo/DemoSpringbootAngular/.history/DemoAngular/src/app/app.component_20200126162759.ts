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

  constructor(
    private httpClient: HttpClient
  ) {

  }

  ngOnInit(): void {
    this.getListNhanVien();
  }

  getListNhanVien(): void {
    this.httpClient.get<NhanVien[]>("http://localhost:8080/TrangChu/NhanVien").subscribe(data => {
      this.lstNhanVien = data;
      this.lstNhanVienNguyenGoc = data;
    });
  }

  // sắp xếp
  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  onSort({ column, direction }: SortEvent) {
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    if (direction === '') {
      this.lstNhanVien = this.lstNhanVienNguyenGoc;
      return;
    }

    this.lstNhanVien = [...this.lstNhanVienNguyenGoc].sort((a, b) => {
      const res = compare(a[column], b[column]);
      return direction === 'asc' ? res : -res;
    });
  }

}

