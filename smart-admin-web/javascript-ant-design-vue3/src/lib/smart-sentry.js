//Error reporting sentry

export const smartSentry = {
  /**
   * sentry take the initiative to report
   */
  captureError: (error) => {
    if (error.config && error.data && error && error.headers && error.request && error.status) {
      return;
    }
    // Sentry.captureException(error);
    console.error(error);
  },
};
