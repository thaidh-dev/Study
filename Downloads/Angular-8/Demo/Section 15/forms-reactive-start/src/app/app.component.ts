import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  genders = ['male', 'female'];
  signupForm: FormGroup;
  forbiddenUsernames = ['Chris', 'Anna'];

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    // const control = new FormControl('', Validators.requiredTrue);
    // console.log(control.errors); // {required: true}
    // tạo thuộc tính 'required'(có giá trị = true nếu check validate fail, ngược lại thì có giá trị null) cho FormControl

    this.signupForm = new FormGroup({
      'userData': new FormGroup({
        'username': new FormControl(null, [Validators.required, this.forbiddenNames.bind(this)]),
        'email': new FormControl(null, [Validators.required, Validators.email], this.forbiddenEmails),
      }),
      'gender': new FormControl('male'),
      'hobbies': new FormArray([])
    });

    // this.signupForm.valueChanges.subscribe((value) => console.log(value)); // bắt sự kiện thay đổi giá trị
    // this.signupForm.statusChanges.subscribe((value) => console.log(value)); // bắt sự kiện thay đổi trạng thái

    // setValue thì phải set đủ hết tất cả các thành phần của formGroup, không thì nó lỗi
    this.signupForm.setValue({
      'userData': {
        'username': 'Max',
        'email': 'max@gmail.com'
      },
      'gender': 'male',
      'hobbies': []
    });

    // vá giá trị, không bắt buộc phải vá tất
    this.signupForm.patchValue({
      'userData': {
        'username': 'Thái'
      },
    });
  }

  onSubmit() {
    console.log(this.signupForm);
  }

  onAddHobby() {
    const input = new FormControl(null, Validators.required);
    (<FormArray>this.signupForm.get('hobbies')).push(input);
    // this.signupForm.get('hobbies').push(input); // 2 cách viết như nhau
  }

  // điền Anna hoặc Chris vào input Username thì input đấy báo đỏ
  forbiddenNames(control: FormControl): { [s: string]: boolean } {
    if (this.forbiddenUsernames.indexOf(control.value) !== -1) {
      return { 'nameIsForbidden': true }; // tạo 1 thuộc tính 'nameIsForbidden'(có giá trị là true) cho formControl 'username'
    }

    return null;
  }

  forbiddenEmails(control: FormControl): Promise<any> | Observable<any> {
    const promise = new Promise<any>((resolve, reject) => {
      setTimeout(() => {
        if (control.value === 'test@test.com') {
          resolve({ 'emailIsForbidden': true });
        } else {
          resolve(null);
        }
      }, 1500);
    });
    // trong khoảng time 1500 thì input email ở trạng thái ng-pending,
    // trạng thái này !signupForm.get('userData.email').valid nên dòng 'Please enter a valid email!' được hiện ra


    return promise;
  }
}
