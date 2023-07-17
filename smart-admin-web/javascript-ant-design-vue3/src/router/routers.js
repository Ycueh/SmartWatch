//All routers entries
import { homeRouters } from './system/home';
import { loginRouters } from './system/login';
import { helpDocRouters } from './support/help-doc';

export const routerArray = [...loginRouters, ...homeRouters, ...helpDocRouters];
