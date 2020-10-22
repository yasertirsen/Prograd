import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  hide = true;
  model: StudentModel = {
    firstName:'',
    surname:'',
    email:'',
    password:''
  };
  constructor(private client: HttpClient) { }

  ngOnInit(): void {
  }

  registerStudent(): void {
    let url = "http://localhost:8082/api/student/add";
    this.client.post(url, this.model).subscribe(
      res => {
        location.reload();
      },
      err => {
        alert("An error has occurred while registering")
      }
    )
  }
}

export interface StudentModel {
  firstName: string;
  surname: string;
  email: string;
  password: string;
}
