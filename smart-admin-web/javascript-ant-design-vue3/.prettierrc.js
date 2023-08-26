/*
 * Code formatting configuration
 *
 */
module.exports = {
  printWidth: 150, // Maximum line length (default 80)
  tabWidth: 2, // Number of spaces per indentation level
  useTabs: false, // Use spaces for indentation instead of tabs
  semi: true, // Print semicolons at the ends of statements
  singleQuote: true, // Use single quotes instead of double quotes
  vueIndentScriptAndStyle: true, // Indent script and style tags in Vue files
  quoteProps: 'as-needed', // When to change the quotes of object properties. Possible values: "<as-needed|consistent|preserve>"
  jsxSingleQuote: true, // Use single quotes in JSX instead of double quotes
  trailingComma: 'es5', // Print trailing commas whenever possible in multiline lists. Possible values: "<none|es5|all>", default is none
  bracketSpacing: true, // Print spaces between braces in object literals
  jsxBracketSameLine: false, // Put the > of a multi-line JSX element at the end of the last line
  arrowParens: 'always', // Include parentheses around a sole arrow function parameter. Possible values: "always|avoid"
  rangeStart: 0, // These two options can be used to format code starting and ending from a given character offset
  rangeEnd: Infinity,
  requirePragma: false, // Specify parser to use, no need to write @prettier at the beginning of files
  insertPragma: false, // Don't automatically insert @prettier at the beginning of files
  proseWrap: 'preserve', // Use default wrapping rules: always|never|preserve
  htmlWhitespaceSensitivity: 'css', // Specify the global whitespace sensitivity for HTML files: css|strict|ignore
  endOfLine: 'auto', // Specify the line ending: auto|lf|crlf|cr
};
