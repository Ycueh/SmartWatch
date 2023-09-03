/*
 * vite settings
 */
import { resolve } from 'path';
import vue from '@vitejs/plugin-vue';

const pathResolve = (dir) => {
  return resolve(__dirname, '.', dir);
};
export default {
  base: process.env.NODE_ENV === 'production' ? '/' : '/',
  root: process.cwd(),
  resolve: {
    alias: [
      // internationalization replacement
      {
        find: 'vue-i18n',
        replacement: 'vue-i18n/dist/vue-i18n.cjs.js',
      },
      // absolute path renaming：/@/xxxx => src/xxxx
      {
        find: /\/@\//,
        replacement: pathResolve('src') + '/',
      },
      {
        find: /^~/,
        replacement: '',
      },
    ],
  },
  // server-side rendering
  server: {
    host: '0.0.0.0',
    port: 8081,
  },
  plugins: [vue()],
  optimizeDeps: {
    include: ['ant-design-vue/es/locale/zh_CN', 'dayjs/locale/zh-cn', 'ant-design-vue/es/locale/en_US'],
    exclude: ['vue-demi'],
  },
  build: {
    // Clear console and debugger
    terserOptions: {
      compress: {
        drop_console: true,
        drop_debugger: true,
      },
    },
    rollupOptions: {
      output: {
        chunkFileNames: 'js/[name]-[hash].js',
        entryFileNames: 'js/[name]-[hash].js',
        assetFileNames: '[ext]/[name]-[hash].[ext]',
        manualChunks(id) {
          //Static resource splitting and packaging
          if (id.includes('node_modules')) {
            return id.toString().split('node_modules/')[1].split('/')[0].toString();
          }
        },
      },
    },
    target: 'modules',
    outDir: 'dist', // Specify the output path
    assetsDir: 'assets', // Specify the directory to generate static files
    assetsInlineLimit: '4096', // Imported or referenced resources smaller than this threshold will be inlined as base64 encoded
    chunkSizeWarningLimit: 500, // Limits on chunk size warnings
    minify: 'terser', // Obfuscator, the file size is smaller after terser construction
    emptyOutDir: true, //Clear the original package file before packaging
  },
  css: {
    preprocessorOptions: {
      less: {
        modifyVars: {
          hack: `true; @import (reference) "${resolve('src/theme/index.less')}";`,
        },
        javascriptEnabled: true,
      },
    },
  },
  define: {
    __INTLIFY_PROD_DEVTOOLS__: false,
    'process.env': process.env,
  },
};



