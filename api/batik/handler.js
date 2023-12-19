const { db } = require("../../config/firebase");

class BatikHandler {
  async getBatikHandler(request, h) {
    const data = await db.collection("BatikIndonesia").get();
    const batik = data.docs.map((doc) => doc.data());
    return {
      status: "success",
      data: {
        batik,
      },
    };
  }
  async getBatikByIdHandler(request) {
    const { id } = request.params;
    const data = await db.collection("batik").doc(id).get();
    const batik = data.data();
    return {
      status: "success",
      data: {
        batik,
      },
    };
  }
  async likeBatikHandler(request, h) {
    const { id: batikId } = request.params;
    const token = request.headers.authorization;
    const userId = await verifyToken(token);

    const snapshot = await db
      .collection("likedBatik")
      .where("batikId", "==", batikId)
      .where("userId", "==", userId)
      .get();

    if (snapshot.empty) {
      await db.collection("likedBatik").add({
        batikId,
        userId,
      });
      return {
        status: "success",
      };
    } else {
      return {
        status: "error",
      };
    }
  }

  async getLikedBatikHandler(request, h) {
    const token = request.headers.authorization;
    const userId = await verifyToken(token);
    const data = await db
      .collection("likedBatik")
      .where("userId", "==", userId)
      .get();
    const batik = data.docs.map((doc) => doc.data());
    const response = h.response({
      status: "success",
      data: {
        batik,
      },
    });
    return response;
  }

  async unLikeBatikHandler(request) {
    const { id } = request.params;
    const token = request.headers.authorization;
    const userId = await verifyToken(token);

    const snapshot = await db
      .collection("likedBatik")
      .where("batikId", "==", id)
      .where("userId", "==", userId)
      .get();

    if (!snapshot.empty) {
      // Ada dokumen yang memenuhi kedua kondisi, lakukan penghapusan
      const deletePromises = snapshot.docs.map((doc) => doc.ref.delete());
      await Promise.all(deletePromises);

      return { status: "success" };
    } else {
      return {
        status: "error",
      };
    }
  }
}

module.exports = BatikHandler;
