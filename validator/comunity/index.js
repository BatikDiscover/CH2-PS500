const InvariantError = require("../../exception/InvariantError");
const {
  PostingPayloadSchema,
  CommentPayloadSchema,
  ImageHeadersSchema,
} = require("./schema");

const ComunityValidator = {
  validatePostingPayload: (payload) => {
    const validationResult = PostingPayloadSchema.validate(payload);
    if (validationResult.error) {
      throw new InvariantError(validationResult.error.message);
    }
  },

  validateCommentPayload: (payload) => {
    const validationResult = CommentPayloadSchema.validate(payload);
    if (validationResult.error) {
      throw new InvariantError(validationResult.error.message);
    }
  },
  validateImageHeaders: (headers) => {
    const validationResult = ImageHeadersSchema.validate(headers);

    if (validationResult.error) {
      throw new InvariantError(validationResult.error.message);
    }
  },
};

module.exports = ComunityValidator;
