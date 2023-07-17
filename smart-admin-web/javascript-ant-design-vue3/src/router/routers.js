//All routers entries
import { homeRouters } from './system/home';
import { loginRouters } from './system/login';

export const routerArray = [...loginRouters, ...homeRouters];
