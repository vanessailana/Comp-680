// src/app/auth/auth.service.ts

import { Observable } from 'rxjs';
import 'rxjs/add/operator/filter';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import * as auth0 from 'auth0-js';
import { of } from 'rxjs';

import 'rxjs/add/observable/timer';
@Injectable()
export class AuthService {

  private _idToken: string;
  private _accessToken: string;
  private _expiresAt: number;
 refreshSubscription: any;
  auth0 = new auth0.WebAuth({
    clientID: 'Rn9gH_Xwv3LL6GkmAs6R9vm7hMEL8DsB',
    domain: 'comp586proj.auth0.com',
    responseType: 'token id_token',
    redirectUri: 'http://localhost:4200/callback',
    scope: 'openid'
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
  }

  public handleAuthentication(): void {
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        this.localLogin(authResult);
        this.router.navigate(['/home']);
      } else if (err) {
        this.router.navigate(['/home']);
        console.log(err);
        alert(`Error: ${err.error}. Check the console for further details.`);
      }
    });
  }

  private localLogin(authResult): void {
    // Set isLoggedIn flag in localStorage
    localStorage.setItem('isLoggedIn', 'true');
    // Set the time that the access token will expire at
    const expiresAt = (authResult.expiresIn * 1000) + new Date().getTime();
    this._accessToken = authResult.accessToken;
    this._idToken = authResult.idToken;
    this._expiresAt = expiresAt;

      this.scheduleRenewal();
  }



  public logout(): void {
    // Remove tokens and expiry time
    this._accessToken = '';
    this._idToken = '';
    this._expiresAt = 0;
    // Remove isLoggedIn flag from localStorage
    localStorage.removeItem('isLoggedIn');
    // Go back to the home route
    this.router.navigate(['/']);
  }
   public renewTokens(): void {
    this.auth0.checkSession({}, (err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        this.localLogin(authResult);
      } else if (err) {
        alert(`Could not get a new token (${err.error}: ${err.error_description}).`);
        this.logout();
      }
    });
  }

  public isAuthenticated(): boolean {
    // Check whether the current time is past the
    // access token's expiry time
    return Date.now() < this._expiresAt;
  }

  public scheduleRenewal() {
    if(!this.isAuthenticated()) return;
    this.unscheduleRenewal();

    const expiresAt = this._expiresAt;

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






}

