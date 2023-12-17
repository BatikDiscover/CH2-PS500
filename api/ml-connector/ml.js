const tfjs = require("@tensorflow/tfjs");
const path = require("path");
const uploadModel = "model.json";
const uploadModelPath = path.join(process.cwd(), uploadModel);

// async function loadModel() {
//   try {
//     const model = await tfjs.loadLayersModel("model.json");
//     return tfjs.loadLayersModel(model);
//   } catch (error) {
//     console.error("Error loading model:", error);
//     throw error;
//   }
// }

async function predict(imageBuffer) {
  const imageBufferLength = imageBuffer.byteLength;
  const paddingLength = 4 - (imageBufferLength % 4);
  const paddedImageBuffer = Buffer.concat([
    imageBuffer,
    Buffer.alloc(paddingLength, 0),
  ]);

  // const uploadModelFile = new File({
  //   buffer: fs.readFileSync(uploadModelPath),
  // });

  const tensor = tfjs.node
    .decodeImage(paddedImageBuffer)
    .resizeNearestNeighbor([224, 224])
    .expandDims()
    .toFloat();

  return model.predict(tensor).data();
}

module.exports = { predict };
