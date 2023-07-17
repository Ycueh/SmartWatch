//String related operations

/**
 * Convert to lower hyphen
 */
export function convertLowerHyphen(str) {
  if (!str) {
    return '';
  }

  return str
    .replace(/([A-Z])/g, '-$1')
    .toLowerCase()
    .substring(1);
}
/**
 * convert to upper camel
 */
export function convertUpperCamel(str) {
  if (!str) {
    return '';
  }

  str = str.replace(/_(\w)/g, (_, letter) => letter.toUpperCase());
  // capitalize the first letter
  return str[0].toUpperCase() + str.substring(1);
}

/**
 * convert to lower camel
 */
export function convertLowerCamel(str) {
  if (!str) {
    return '';
  }

  return str.replace(/_(\w)/g, (_, letter) => letter.toUpperCase());
}
