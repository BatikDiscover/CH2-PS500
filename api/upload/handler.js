const autoBind = require("auto-bind");
const fs = require("fs");
const {
  getStorage,
  ref,
  uploadBytesResumable,
  getDownloadURL,
} = require("firebase/storage");
class UploadsHandler {
  constructor(service, validator) {
    this._service = service;
    this._validator = validator;

    autoBind(this);
  }

  async postUploadImageHandler(request, h) {
    const { id } = request.params;
    const { image } = request.payload;
    this._validator.validateImageHeaders(image.hapi.headers);
    const meta = image.hapi;
    const filename = +new Date() + meta.filename;

    const storage = getStorage();
    const imageRef = ref(storage, "postings/" + filename);
    const upload = await uploadBytesResumable(imageRef, image._data);
    const downloadURL = await getDownloadURL(imageRef);

    const response = h.response({
      status: "success",
      message: "Gambar berhasil diunggah",
      data: downloadURL,
    });
    response.code(201);
    return response;
  }
}
module.exports = UploadsHandler;
