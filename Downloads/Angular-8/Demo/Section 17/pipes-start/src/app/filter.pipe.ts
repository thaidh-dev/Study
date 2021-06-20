import { Pipe, PipeTransform } from '@angular/core';

/* 
  có 2 thể loại của pipes: pure và impure, pure là default
  set pipe là impure = cách set false cho pure

  input ở đây là thành phần chuẩn bị được pipe

  pure: 
    pure pipe được thực hiện khi phát hiện pure change ở input value. 
    pure change là thay đổi giá trị nguyên thủy của input(String, Number, Boolean, Symbol) 
      hoặc thay đổi tham chiếu đối tượng(Date, Array, Function, Object).
    
    angular bỏ qua những thay đổi trong đối tượng. 
    nó sẽ không thực hiện pipe nếu thêm vào input 1 array, thay đổi thuộc tính của input 

    làm thế thì nó nhanh.
    Kiểm tra tham chiếu đối tượng là nhanh hơn nhiều so với kiểm tra sâu về sự khác biệt, 
      vì vậy Angular bỏ qua cả việc thực hiện pipe và cập nhật view.

  impure:
    được gọi khi có bất kì 1 thay đổi nào của page như nhấn phím hoặc unFocus
    

*/
@Pipe({
  name: 'filter',
  pure: false
})
export class FilterPipe implements PipeTransform {
  transform(value: any, filterString: string, propName: string): any {
    if (value.length === 0 || filterString === '')
      return value;

    const resultArray = [];
    for (const item of value)
      if (item[propName] === filterString)
        resultArray.push(item);

    return resultArray;
  }
}
