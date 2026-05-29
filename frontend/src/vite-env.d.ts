/// <reference types="vite/client" />

//typescript TypeScript doesn’t understand .vue files, therefore we need this
declare module '*.vue' {
  import { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}
