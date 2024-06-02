import { Injectable } from '@angular/core';
import {Storage, ref, uploadBytes, getDownloadURL} from '@angular/fire/storage'

@Injectable({
  providedIn: 'root'
})
export class FirebaseStorageServiceService {

  constructor(private storage: Storage) { }


  async uploadImg(filePath: string, file: File) {
    const storageRef = ref(this.storage, filePath);
    const uploadResult = await uploadBytes(storageRef, file);
    return getDownloadURL(uploadResult.ref);
  }
}
