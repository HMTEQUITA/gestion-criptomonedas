import { TestBed } from '@angular/core/testing';

import { AddcryptoService } from './addcrypto.service';

describe('AddcryptoService', () => {
  let service: AddcryptoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddcryptoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
