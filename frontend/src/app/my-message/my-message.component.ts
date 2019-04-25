import { Component, OnInit } from '@angular/core';
import { MyMessageService } from './my-message.service';


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

  constructor( messageService : MyMessageService) { 

    this.user = JSON.parse(localStorage.getItem('user'));

   
  

    messageService.getFromMessage(this.user.id).subscribe(
      (res)=>{
        
        this.fromMessages = res

      },
      (err)=>console.log(err),
      () => {

       
      }


    
    )

    messageService.getToMessage(this.user.id).subscribe(
      (res)=>this.toMessages = res,
      (err)=>console.log(err),
      ()=>{
        console.log(JSON.stringify(this.toMessages));
        this.messages = this.fromMessages.concat(this.toMessages);
        console.log("Messages"+this.messages);

        var sort = [];

        this.fromUsers = new Array<any>();
        
        //this.messages.sort((a, b) => (a.toUser > b.toUser && a.fromUser > b.fromUser) ? 1 : -1);

        console.log(this.messages);

        var distinct:Array<number> = []
        for (var i = 0; i < this.messages.length; i++)
        {
          if (!distinct.find((e)=> e == this.messages[i].fromUser))
          {
              distinct.push(this.messages[i].fromUser);
          }
        }
          
              messageService.getFromUser(distinct).subscribe(
                (res)=>{this.fromUsers = res },
                (err)=>{},
                ()=>{

                  this.fromUsers = this.fromUsers.filter((e)=>e!=null);
                  console.log(this.fromUsers);

                }
              )          
         
        
      }
    )

    
    

  }


  myId()
  {
    return this.user.id;
  }

  displayMessage(fromId)
  {

    this.currentMessages = new Array<any>();

    this.messages.forEach((e)=>{
      if((e.fromUser==this.user.id&&e.toUser==fromId)||
      (e.fromUser==fromId&&e.toUser==this.user.id))
      {
        this.currentMessages.push(e);
      }
    })

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

  ngOnInit() {
  }

}
