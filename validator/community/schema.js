const Joi = require("joi");

const ImageHeadersSchema = Joi.object({
  "content-type": Joi.string().valid(
    "image/apng",
    "image/avif",
    "image/gif",
    "image/jpeg",
    "image/jpg",
    "image/png",
    "image/webp"
  ),
}).unknown();

const PostingPayloadSchema = Joi.object({
  title: Joi.string().required(),
  content: Joi.string().required(),
  image: ImageHeadersSchema,
});

const CommentPayloadSchema = Joi.object({
  content: Joi.string().required(),
});

module.exports = {
  PostingPayloadSchema,
  CommentPayloadSchema,
  ImageHeadersSchema,
};
