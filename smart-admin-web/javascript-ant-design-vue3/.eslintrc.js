/*
 * @Description:
 * @Author: zhuoda
 * @Date: 2021-11-05
 * @LastEditTime: 2022-07-05
 * @LastEditors: zhuoda
 */
module.exports = {
  root: true, // This option is used to indicate that ESLint should stop looking for configuration files in parent directories
  env: {
    browser: true,
    es2021: true,
    node: true,
  },
  parser: 'vue-eslint-parser', // Use vue-eslint-parser to parse template and script in Vue files
  parserOptions: {
    ecmaVersion: 12, // By default, ESLint uses ECMAScript 5 syntax. Here we set it to es12.
    sourceType: 'module', // Specify how JS files should be interpreted as modules
  },
  extends: ['plugin:vue/vue3-essential', 'eslint:recommended', 'plugin:vue/base'],
  globals: {
    defineProps: 'readonly',
    defineEmits: 'readonly',
    defineExpose: 'readonly',
    withDefaults: 'readonly',
  },
  plugins: ['vue'],
  rules: {
    'no-unused-vars': [
      'error',
      // We are using this rule only to check for unused arguments since TypeScript
      // catches unused variables but not args.
      { varsIgnorePattern: '.*', args: 'none' },
    ],
    'space-before-function-paren': 'off',

    'vue/attributes-order': 'off',
    'vue/one-component-per-file': 'off',
    'vue/html-closing-bracket-newline': 'off',
    'vue/max-attributes-per-line': 'off',
    'vue/multiline-html-element-content-newline': 'off',
    'vue/singleline-html-element-content-newline': 'off',
    'vue/attribute-hyphenation': 'off',
    'vue/require-default-prop': 'off',
    'vue/multi-word-component-names': [
      'error',
      {
        ignores: ['index'], // Component names to be ignored
      },
    ],
    'vue/html-self-closing': [
      'error',
      {
        html: {
          void: 'always',
          normal: 'never',
          component: 'always',
        },
        svg: 'always',
        math: 'always',
      },
    ],
    // Enable vue/script-setup-uses-vars rule
    'vue/script-setup-uses-vars': 'error',
  },
};
