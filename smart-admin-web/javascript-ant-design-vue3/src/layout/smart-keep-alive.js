/*
 *  keep-alive
 */
import { computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useUserStore } from '/@/store/modules/system/user';

export function smartKeepAlive() {
  const route = useRoute();
  const router = useRouter();
  // keep-alive pages
  const keepAliveIncludes = computed(() => {
    return useUserStore().keepAliveIncludes || [];
  });
  
  // ----------------------- related to iframe -----------------------

  // Whether the current route is an iframe page that does not need to be cached
  const iframeNotKeepAlivePageFlag = computed(() => route.meta.frameFlag && !route.meta.keepAlive);
  // opened tagNav
  const tagNav = computed(() => useUserStore().getTagNav || []);
  // List of opened iframe pages
  const keepAliveIframePages = computed(() => {
    let routes = router.getRoutes();
    return routes.filter((e) => e.meta.frameFlag && e.meta.keepAlive && tagNav.value.some((t) => t.menuName == e.name));
  });
  return {
    route,
    keepAliveIncludes,
    iframeNotKeepAlivePageFlag,
    keepAliveIframePages,
  };
}
