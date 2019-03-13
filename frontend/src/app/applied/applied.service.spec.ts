import { TestBed } from '@angular/core/testing';

import { AppliedService } from './applied.service';

describe('AppliedService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AppliedService = TestBed.get(AppliedService);
    expect(service).toBeTruthy();
  });
});
