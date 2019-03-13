// src/app/auth/auth.service.ts

import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import * as auth0 from 'auth0-js';
import { map, filter, catchError, mergeMap } from 'rxjs/operators';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/timer';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/mergeMap'
import 'rxjs/add/operator/filter';
@Injectable()
export class AuthService {
  private _idToken: string;
  private _accessToken: string;
  private _expiresAt: number;
  userProfile: any;
    refreshSubscription: any;
  requestedScopes: string = 'openid profile';
    auth0 = new auth0.WebAuth({
    clientID: 'Rn9gH_Xwv3LL6GkmAs6R9vm7hMEL8DsB',
    domain: 'comp586proj.auth0.com',
    responseType: 'token id_token',
    redirectUri: 'http://localhost:4200/callback',
    scope: 'openid profile email'
  });


constructor(public router: Router) {
    this._idToken = '';
    this._accessToken = '';
    this._expiresAt = 0;

  }

  get accessToken(): string {
    return this._accessToken;
  }

  get idToken(): string {
    return this._idToken;
  }
  public login(): void {
    this.auth0.authorize();
    console.log("login");
  }

  public handleAuthentication(): void {
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken ) {
        this.setSession(authResult);
        console.log(authResult);
        console.log("teste");
        this.router.navigate(['/profile']);
      } else if (err) {
        this.router.navigate(['']);
       

      }
    });
  }


  private _checkAdmin(profile) {
    // Check if the user has admin role
    const roles = profile['https://example.com/roles'] || [];
    
     localStorage.setItem('roles', roles);
       localStorage.setItem('name', name);
    return roles.indexOf('admin') > -1;
  }

  


public getProfile(cb): void {
    const accessToken = localStorage.getItem('access_token');
  
    const self = this;
    this.auth0.client.userInfo(accessToken, (err, profile) => {
      if (profile && this._checkAdmin(profile)) {
        self.userProfile = profile;
         
     

      }
        if (profile && !this._checkAdmin(profile)) {
        self.userProfile = profile;
        console.log("test1");

      }
      cb(err, profile);
    });
  }
  private setSession(authResult): void {

    // Set the time that the access token will expire at
    const expiresAt = JSON.stringify((authResult.expiresIn * 1000) + new Date().getTime());

    // If there is a value on the `scope` param from the authResult,
    // use it to set scopes in the session for the user. Otherwise
    // use the scopes as requested. If no scopes were requested,
    // set it to nothing
    const scopes = authResult.scope || this.requestedScopes || '';

    localStorage.setItem('access_token', authResult.accessToken);
    localStorage.setItem('id_token', authResult.nickname);
    localStorage.setItem('expires_at', expiresAt);
    localStorage.setItem('scopes', JSON.stringify(scopes));
   
    this.scheduleRenewal();
  }

  public logout(): void {
    // Remove tokens and expiry time from localStorage
    localStorage.removeItem('access_token');
    localStorage.removeItem('id_token');
    localStorage.removeItem('expires_at');
    localStorage.removeItem('user');
    localStorage.removeItem('scopes');
       localStorage.removeItem('roles');
    // Go back to the home route
    this.router.navigate(['/']);
    location.reload();
  }


  public isAuthenticated(): boolean {
    // Check whether the current time is past the
    // access token's expiry time
    const expiresAt = JSON.parse(localStorage.getItem('expires_at') || '{}');
    return new Date().getTime() < expiresAt;
  }
   public renewTokens() {
    this.auth0.checkSession({}, (err, result) => {
      if (err) {
        console.log('Could not get a new token')
      } else {
        console.log("token has been renewed");
        this.setSession(result);

      }
    });
  }
 public scheduleRenewal() {
    if(!this.isAuthenticated()) return;
    this.unscheduleRenewal();

    const expiresAt = JSON.parse(window.localStorage.getItem('expires_at'));

    const source = Observable.of(expiresAt).flatMap(
      expiresAt => {

        const now = Date.now();

        // Use the delay in a timer to
        // run the refresh at the proper time
        return Observable.timer(Math.max(1, expiresAt - now));
      });

    // Once the delay time from above is
    // reached, get a new JWT and schedule
    // additional refreshes
    this.refreshSubscription = source.subscribe(() => {
      this.renewTokens();
      this.scheduleRenewal();
    });
  }

  public unscheduleRenewal() {
    if(!this.refreshSubscription) return;
    this.refreshSubscription.unsubscribe();
  }
  public userHasScopes(scopes: Array<string>): boolean {
    const grantedScopes = JSON.parse(localStorage.getItem('scopes')).split(' ');
    return scopes.every(scope => grantedScopes.includes(scope));

  }

}


