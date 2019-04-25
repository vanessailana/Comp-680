import { TestBed } from '@angular/core/testing';

import { MyMessageService } from './my-message.service';

describe('MyMessageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MyMessageService = TestBed.get(MyMessageService);
    expect(service).toBeTruthy();
  });
});
