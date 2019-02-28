import { Component, OnInit } from '@angular/core';
import { AuthService } from './../auth/auth.service';
<<<<<<< HEAD
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';


=======
>>>>>>> Mail-Handler-backend
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
<<<<<<< HEAD
  closeResult: string;
  profile: any;

  constructor(public auth: AuthService, private modalService: NgbModal) { }
=======

  profile: any;

  constructor(public auth: AuthService) { }
>>>>>>> Mail-Handler-backend

  ngOnInit() {
    if (this.auth.userProfile) {
      this.profile = this.auth.userProfile;
    } else {
      this.auth.getProfile((err, profile) => {
        this.profile = profile;
      });
    }
    }
<<<<<<< HEAD

    open(content) {
      this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
      }, (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      });
    }
  
    private getDismissReason(reason: any): string {
      if (reason === ModalDismissReasons.ESC) {
        return 'by pressing ESC';
      } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
        return 'by clicking on a backdrop';
      } else {
        return  `with: ${reason}`;
      }
    }
}
=======
  }
>>>>>>> Mail-Handler-backend
