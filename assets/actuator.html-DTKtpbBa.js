import{_ as t,c as e,b as n,d as a,e as l,f as o,a as c,r as i,o as u}from"./app-DuW-mWuI.js";const r={};function k(d,s){const p=i("RouteLink");return u(),e("div",null,[s[3]||(s[3]=n("h1",{id:"健康检查",tabindex:"-1"},[n("a",{class:"header-anchor",href:"#健康检查"},[n("span",null,"健康检查")])],-1)),n("blockquote",null,[n("p",null,[s[1]||(s[1]=a("请确保您已经安装了 spring-cloud-stream-redis，如果您尚未安装，请查看 ")),l(p,{to:"/guide/install.html"},{default:o(()=>s[0]||(s[0]=[a("安装")])),_:1}),s[2]||(s[2]=a("。"))])]),s[4]||(s[4]=c(`<h2 id="开启配置" tabindex="-1"><a class="header-anchor" href="#开启配置"><span>开启配置</span></a></h2><div class="language-yaml line-numbers-mode" data-highlighter="prismjs" data-ext="yml" data-title="yml"><pre><code><span class="line"><span class="token key atrule">management</span><span class="token punctuation">:</span></span>
<span class="line">  <span class="token key atrule">endpoints</span><span class="token punctuation">:</span></span>
<span class="line">    <span class="token key atrule">web</span><span class="token punctuation">:</span></span>
<span class="line">      <span class="token key atrule">exposure</span><span class="token punctuation">:</span></span>
<span class="line">        <span class="token key atrule">include</span><span class="token punctuation">:</span> <span class="token string">&#39;*&#39;</span></span>
<span class="line">  <span class="token key atrule">endpoint</span><span class="token punctuation">:</span></span>
<span class="line">    <span class="token key atrule">health</span><span class="token punctuation">:</span></span>
<span class="line">      <span class="token key atrule">show-details</span><span class="token punctuation">:</span> always</span>
<span class="line">  <span class="token key atrule">health</span><span class="token punctuation">:</span></span>
<span class="line">    <span class="token key atrule">binders</span><span class="token punctuation">:</span></span>
<span class="line">      <span class="token key atrule">enabled</span><span class="token punctuation">:</span> <span class="token boolean important">true</span></span>
<span class="line">    <span class="token key atrule">redis</span><span class="token punctuation">:</span></span>
<span class="line">      <span class="token key atrule">enabled</span><span class="token punctuation">:</span> <span class="token boolean important">false</span></span>
<span class="line"></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h2 id="访问路径" tabindex="-1"><a class="header-anchor" href="#访问路径"><span>访问路径</span></a></h2><p><code>/actuator/health</code></p><p>示例：</p><div class="language-json" data-highlighter="prismjs" data-ext="json" data-title="json"><pre><code><span class="line"><span class="token punctuation">{</span></span>
<span class="line">    <span class="token property">&quot;status&quot;</span><span class="token operator">:</span> <span class="token string">&quot;UP&quot;</span><span class="token punctuation">,</span></span>
<span class="line">    <span class="token property">&quot;components&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">        <span class="token property">&quot;binders&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">            <span class="token property">&quot;status&quot;</span><span class="token operator">:</span> <span class="token string">&quot;UP&quot;</span><span class="token punctuation">,</span></span>
<span class="line">            <span class="token property">&quot;components&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">                <span class="token property">&quot;redis&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">                    <span class="token property">&quot;status&quot;</span><span class="token operator">:</span> <span class="token string">&quot;UP&quot;</span><span class="token punctuation">,</span></span>
<span class="line">                    <span class="token property">&quot;details&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">                        <span class="token property">&quot;version&quot;</span><span class="token operator">:</span> <span class="token string">&quot;7.1.2&quot;</span><span class="token punctuation">,</span></span>
<span class="line">                        <span class="token property">&quot;pool.maxTotal&quot;</span><span class="token operator">:</span> <span class="token number">8</span><span class="token punctuation">,</span></span>
<span class="line">                        <span class="token property">&quot;pool.maxIdle&quot;</span><span class="token operator">:</span> <span class="token number">8</span><span class="token punctuation">,</span></span>
<span class="line">                        <span class="token property">&quot;pool.minIdle&quot;</span><span class="token operator">:</span> <span class="token number">0</span></span>
<span class="line">                    <span class="token punctuation">}</span></span>
<span class="line">                <span class="token punctuation">}</span></span>
<span class="line">            <span class="token punctuation">}</span></span>
<span class="line">        <span class="token punctuation">}</span><span class="token punctuation">,</span></span>
<span class="line">        <span class="token property">&quot;ping&quot;</span><span class="token operator">:</span> <span class="token punctuation">{</span></span>
<span class="line">            <span class="token property">&quot;status&quot;</span><span class="token operator">:</span> <span class="token string">&quot;UP&quot;</span></span>
<span class="line">        <span class="token punctuation">}</span></span>
<span class="line">    <span class="token punctuation">}</span></span>
<span class="line"><span class="token punctuation">}</span></span>
<span class="line"></span></code></pre></div>`,6))])}const q=t(r,[["render",k],["__file","actuator.html.vue"]]),v=JSON.parse('{"path":"/guide/actuator.html","title":"健康检查","lang":"zh-cn","frontmatter":{"lang":"zh-cn","title":"健康检查","description":null},"headers":[{"level":2,"title":"开启配置","slug":"开启配置","link":"#开启配置","children":[]},{"level":2,"title":"访问路径","slug":"访问路径","link":"#访问路径","children":[]}],"git":{"updatedTime":1735867441000,"contributors":[{"name":"yanghq","username":"yanghq","email":"1040926235@qq.com","commits":3,"url":"https://github.com/yanghq"}]},"filePathRelative":"guide/actuator.md"}');export{q as comp,v as data};
