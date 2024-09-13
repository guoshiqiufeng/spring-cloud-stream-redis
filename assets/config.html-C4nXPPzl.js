import{_ as e,r as t,c as l,b as s,d as a,e as c,w as o,a as i,o as u}from"./app-DVQmWKwI.js";const r={};function k(d,n){const p=t("RouteLink");return u(),l("div",null,[n[3]||(n[3]=s("h1",{id:"配置",tabindex:"-1"},[s("a",{class:"header-anchor",href:"#配置"},[s("span",null,"配置")])],-1)),n[4]||(n[4]=s("p",null,[s("a",{href:"https://github.com/guoshiqiufeng/spring-cloud-stream-redis",target:"_blank",rel:"noopener noreferrer"},"spring-cloud-stream-redis"),a(" 的配置异常的简单，我们仅需要一些简单的配置即可！")],-1)),s("blockquote",null,[s("p",null,[n[1]||(n[1]=a("请确保您已经安装了 spring-cloud-stream-redis，如果您尚未安装，请查看 ")),c(p,{to:"/guide/install.html"},{default:o(()=>n[0]||(n[0]=[a("安装")])),_:1}),n[2]||(n[2]=a("。"))])]),n[5]||(n[5]=i(`<h2 id="redis" tabindex="-1"><a class="header-anchor" href="#redis"><span>Redis</span></a></h2><h3 id="application-yml-配置-连接参数" tabindex="-1"><a class="header-anchor" href="#application-yml-配置-连接参数"><span><code>application.yml</code> 配置 连接参数</span></a></h3><blockquote><p>支持redis单机版、集群、哨兵模式</p><p>支持<code>spring.data.redis</code>下的所有配置</p></blockquote><div class="language-yaml" data-highlighter="prismjs" data-ext="yml" data-title="yml"><pre><code><span class="line"><span class="token key atrule">spring</span><span class="token punctuation">:</span></span>
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
<span class="line"></span></code></pre></div><h3 id="可选客户端" tabindex="-1"><a class="header-anchor" href="#可选客户端"><span>可选客户端</span></a></h3><p>默认与 使用<code>lettuce</code>客户端，如需切换 jedis 客户端，可以添加 jedis 依赖 即可，配置参数与 spring-boot-starter-data-redis 一致。</p><div class="language-yaml" data-highlighter="prismjs" data-ext="yml" data-title="yml"><pre><code><span class="line"><span class="token key atrule">spring</span><span class="token punctuation">:</span></span>
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
<span class="line"></span></code></pre></div>`,7))])}const m=e(r,[["render",k],["__file","config.html.vue"]]),g=JSON.parse('{"path":"/guide/config.html","title":"配置","lang":"zh-cn","frontmatter":{"lang":"zh-cn","title":"配置","description":null},"headers":[{"level":2,"title":"Redis","slug":"redis","link":"#redis","children":[{"level":3,"title":"application.yml 配置 连接参数","slug":"application-yml-配置-连接参数","link":"#application-yml-配置-连接参数","children":[]},{"level":3,"title":"可选客户端","slug":"可选客户端","link":"#可选客户端","children":[]}]}],"git":{"updatedTime":1725629140000,"contributors":[{"name":"yanghq","email":"1040926235@qq.com","commits":2}]},"filePathRelative":"guide/config.md"}');export{m as comp,g as data};
