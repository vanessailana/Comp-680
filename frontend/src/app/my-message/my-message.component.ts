import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MyMessageService } from './my-message.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { ProfileService } from '../profile/profile.service';


@Component({
  selector: 'app-my-message',
  templateUrl: './my-message.component.html',
  styleUrls: ['./my-message.component.css']
})
export class MyMessageComponent implements OnInit {

  user : any;
  fromMessages : Array<any>;
  toMessages : Array<any>;
  messages : Array<any>;

  fromUserIds : Array<number>;

  fromUsers: Array<any>;

  currentMessages: Array<any>;

  messageForm : FormGroup; 


  toUserId : number;

  dontUpdate = false;

  profile : any;


  constructor( private messageService : MyMessageService,  private fb : FormBuilder, private ref: ChangeDetectorRef,
    private auth: AuthService,
    private profileService: ProfileService) { 


    if(this.profile)
    {

      this.getUser();
    

    }else{


      this.auth.getProfile((err, profile) => {

        this.profile = profile;

        this.getUser();

      });
       
      



    }

    
   

  }

  private getUser()
  {
    if(this.profile.email)
    {
    this.profileService.getUser(this.profile.email).subscribe(
      (res)=>{
        this.user = res;
        this.toUserId = -1;
        this.currentMessages = new Array<any>();
        this.fromMessages = new Array<any>();
        this.toMessages = new Array<any>();
        this.loadMessages();
    
        this.messageForm = this.fb.group({
          toUser : ["",Validators.required],
          fromUser : ["",Validators.required],
          message : ["", Validators.required],
          sentAtDate: ["",Validators.required]
        })
      }
    )
   }
  }
  loadMessages(){ 




    this.messages = new Array<any>();

    this.fromUsers = new Array<any>();

    this.messageService.getMessages(this.user.id).subscribe((res)=>{
      //no need to update frontend
  
      
      if(this.messages.length != res.length)
      {
        

      var difference = res.length - this.messages.length; 
        console.log("RESOURCE"+JSON.stringify(res));

      this.messages = res;
      this.messages.sort(function(a,b){ return a.sentAtDate - b.sentAtDate});

      

      console.log("Messages"+res);


      var distinct:Array<number> = []
      for (var i = 0; i < this.messages.length; i++)
      {
        if (!distinct.find((e)=> e == this.messages[i].fromUser))
        {
            distinct.push(this.messages[i].fromUser);
        }
      }

      this.messageService.getFromUser(distinct).subscribe(
        (res)=>{this.fromUsers = res },
        (err)=>{},
        ()=>{
          this.fromUsers = this.fromUsers.filter((e)=>e!=null);
          console.log("From"+JSON.stringify(this.fromUsers));
          if(this.toUserId > -1)
          {
            this.displayMessage(this.toUserId);
          }
          
        }
      )


      }
    })

   
  }


  myId()
  {
    return this.user.id;
  }

  displayMessage(fromId)
  {

    console.log("OUTSIDE");

    this.toUserId = fromId;

    this.messageForm.controls['toUser'].setValue(this.toUserId);
    this.messageForm.controls['fromUser'].setValue(this.myId());
    this.messageForm.controls['sentAtDate'].setValue(Date.now());
    
    while(this.currentMessages.length > 0)
    {
      this.currentMessages.pop();
    }

  
    this.messages.forEach((e)=>{
      if((e.fromUser==this.user.id&&e.toUser==fromId)||
      (e.fromUser==fromId&&e.toUser==this.user.id))
      {
        this.currentMessages.push(e);
      }
    })

    console.log("Current"+JSON.stringify(this.currentMessages));

    this.updateScroll();
  }

  email(fromId:number)
  { 
   
    var result  = this.fromUsers.find((e)=>(e.id==fromId));
    return result == null ? null : result.email;
  }
  
  updateScroll()
  {
    var element = document.getElementById("messageDIV");
    element.scrollTop = element.scrollHeight;
  }

  get diagnostic() { return JSON.stringify(this.messageForm.value); }


  date(value) { return new Date(Number(value)) }
  
  onSubmit(content)
  {
    if(this.messageForm.valid)
    {
    this.messageService.postMessage(this.messageForm.value).subscribe((res)=>{
        if(res==true)
        {
          console.log("load");
          this.loadMessages();

          
          
          
        }
      },
      (err)=>console.log(err),
      ()=>{this.updateScroll();}
    )
    }
  }

  ngOnInit() {
  }

}
