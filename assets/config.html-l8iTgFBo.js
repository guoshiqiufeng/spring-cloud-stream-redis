import{_ as p,r as t,c as l,b as s,d as a,e as o,w as c,a as i,o as u}from"./app-DVQmWKwI.js";const r={};function k(d,n){const e=t("RouteLink");return u(),l("div",null,[n[3]||(n[3]=s("h1",{id:"configure",tabindex:"-1"},[s("a",{class:"header-anchor",href:"#configure"},[s("span",null,"Configure")])],-1)),n[4]||(n[4]=s("p",null,[s("a",{href:"https://github.com/guoshiqiufeng/spring-cloud-stream-redis",target:"_blank",rel:"noopener noreferrer"},"spring-cloud-stream-redis"),a(" is very easy to configure, we just need some simple configuration！")],-1)),s("blockquote",null,[s("p",null,[n[1]||(n[1]=a("ake sure you have spring-cloud-stream-redis installed，if you haven't, check out the ")),o(e,{to:"/en/guide/install.html"},{default:c(()=>n[0]||(n[0]=[a("Install")])),_:1}),n[2]||(n[2]=a("。"))])]),n[5]||(n[5]=i(`<h2 id="redis" tabindex="-1"><a class="header-anchor" href="#redis"><span>Redis</span></a></h2><h3 id="application-yml-configure-connection-parameters" tabindex="-1"><a class="header-anchor" href="#application-yml-configure-connection-parameters"><span><code>application.yml</code> Configure connection parameters</span></a></h3><blockquote><p>Support Redis Standalone, Cluster, and Sentinel modes</p><p>Support all configurations under <code>spring.data.redis</code></p></blockquote><div class="language-yaml" data-highlighter="prismjs" data-ext="yml" data-title="yml"><pre><code><span class="line"><span class="token key atrule">spring</span><span class="token punctuation">:</span></span>
<span class="line">  <span class="token key atrule">cloud</span><span class="token punctuation">:</span></span>
<span class="line">    <span class="token key atrule">stream</span><span class="token punctuation">:</span></span>
<span class="line">      <span class="token key atrule">default-binder</span><span class="token punctuation">:</span> redis</span>
<span class="line">      <span class="token key atrule">binders</span><span class="token punctuation">:</span></span>
<span class="line">        <span class="token key atrule">redis</span><span class="token punctuation">:</span></span>
<span class="line">          <span class="token key atrule">type</span><span class="token punctuation">:</span> redis</span>
<span class="line">      <span class="token key atrule">redis</span><span class="token punctuation">:</span></span>
<span class="line">        <span class="token key atrule">binder</span><span class="token punctuation">:</span></span>
<span class="line">          <span class="token key atrule">configuration</span><span class="token punctuation">:</span></span>
<span class="line">            <span class="token key atrule">host</span><span class="token punctuation">:</span> 127.0.0.1</span>
<span class="line">            <span class="token key atrule">port</span><span class="token punctuation">:</span> <span class="token number">6379</span></span>
<span class="line">            <span class="token key atrule">password</span><span class="token punctuation">:</span> <span class="token number">123456</span></span>
<span class="line">            <span class="token key atrule">database</span><span class="token punctuation">:</span> <span class="token number">7</span></span>
<span class="line">          <span class="token key atrule">support-type</span><span class="token punctuation">:</span> queue_channel</span>
<span class="line"><span class="token comment">#        bindings:</span></span>
<span class="line"><span class="token comment">#          send-in-0:</span></span>
<span class="line"><span class="token comment">#            consumer:</span></span>
<span class="line"><span class="token comment">#              destination-is-pattern: true</span></span>
<span class="line">      <span class="token key atrule">bindings</span><span class="token punctuation">:</span></span>
<span class="line">        <span class="token key atrule">out-0</span><span class="token punctuation">:</span></span>
<span class="line">          <span class="token key atrule">destination</span><span class="token punctuation">:</span> test<span class="token punctuation">-</span>topic</span>
<span class="line">          <span class="token key atrule">content-type</span><span class="token punctuation">:</span> text/plain</span>
<span class="line">          <span class="token key atrule">group</span><span class="token punctuation">:</span> push<span class="token punctuation">-</span>producer<span class="token punctuation">-</span>group</span>
<span class="line">        <span class="token key atrule">send-in-0</span><span class="token punctuation">:</span></span>
<span class="line">          <span class="token key atrule">destination</span><span class="token punctuation">:</span> test<span class="token punctuation">-</span>topic</span>
<span class="line">          <span class="token key atrule">content-type</span><span class="token punctuation">:</span> text/plain</span>
<span class="line">          <span class="token key atrule">group</span><span class="token punctuation">:</span> test<span class="token punctuation">-</span>send<span class="token punctuation">-</span>group</span>
<span class="line"></span></code></pre></div><h3 id="optional-clients" tabindex="-1"><a class="header-anchor" href="#optional-clients"><span>Optional Clients</span></a></h3><p>The default is to use the <code>lettuce</code> client, if you need to switch to the jedis client, you can add a jedis dependency to do so, with the same configuration parameters as spring-boot-starter-data-redis.</p><div class="language-yaml" data-highlighter="prismjs" data-ext="yml" data-title="yml"><pre><code><span class="line"><span class="token key atrule">spring</span><span class="token punctuation">:</span></span>
<span class="line">  <span class="token key atrule">cloud</span><span class="token punctuation">:</span></span>
<span class="line">    <span class="token key atrule">stream</span><span class="token punctuation">:</span></span>
<span class="line">      <span class="token key atrule">default-binder</span><span class="token punctuation">:</span> redis</span>
<span class="line">      <span class="token key atrule">binders</span><span class="token punctuation">:</span></span>
<span class="line">        <span class="token key atrule">redis</span><span class="token punctuation">:</span></span>
<span class="line">          <span class="token key atrule">type</span><span class="token punctuation">:</span> redis</span>
<span class="line">      <span class="token key atrule">redis</span><span class="token punctuation">:</span></span>
<span class="line">        <span class="token key atrule">binder</span><span class="token punctuation">:</span></span>
<span class="line">          <span class="token key atrule">configuration</span><span class="token punctuation">:</span></span>
<span class="line">            <span class="token key atrule">host</span><span class="token punctuation">:</span> 127.0.0.1</span>
<span class="line">            <span class="token key atrule">port</span><span class="token punctuation">:</span> <span class="token number">6379</span></span>
<span class="line">            <span class="token key atrule">password</span><span class="token punctuation">:</span> <span class="token number">123456</span></span>
<span class="line">            <span class="token key atrule">database</span><span class="token punctuation">:</span> <span class="token number">7</span></span>
<span class="line">            <span class="token key atrule">client-type</span><span class="token punctuation">:</span> jedis</span>
<span class="line">            <span class="token key atrule">jedis</span><span class="token punctuation">:</span></span>
<span class="line">            <span class="token key atrule">pool</span><span class="token punctuation">:</span></span>
<span class="line">              <span class="token key atrule">enabled</span><span class="token punctuation">:</span> <span class="token boolean important">true</span></span>
<span class="line">              <span class="token key atrule">max-idle</span><span class="token punctuation">:</span> <span class="token number">8</span></span>
<span class="line">              <span class="token key atrule">max-active</span><span class="token punctuation">:</span> <span class="token number">8</span></span>
<span class="line"></span>
<span class="line"></span></code></pre></div>`,7))])}const y=p(r,[["render",k],["__file","config.html.vue"]]),g=JSON.parse('{"path":"/en/guide/config.html","title":"Configure","lang":"en-US","frontmatter":{"lang":"en-US","title":"Configure","description":"Configure"},"headers":[{"level":2,"title":"Redis","slug":"redis","link":"#redis","children":[{"level":3,"title":"application.yml Configure connection parameters","slug":"application-yml-configure-connection-parameters","link":"#application-yml-configure-connection-parameters","children":[]},{"level":3,"title":"Optional Clients","slug":"optional-clients","link":"#optional-clients","children":[]}]}],"git":{"updatedTime":1725629140000,"contributors":[{"name":"yanghq","email":"1040926235@qq.com","commits":2}]},"filePathRelative":"en/guide/config.md"}');export{y as comp,g as data};
