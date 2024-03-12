import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  http = inject(HttpClient);
  onSubmit(form: NgForm) {
    this.http.post(`http://localhost:1205/banking/api/v1/accounts`, form.value).subscribe(res => {
      console.log(res);
    });
  }
}
