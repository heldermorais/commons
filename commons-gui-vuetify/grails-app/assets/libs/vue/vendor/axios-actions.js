/**
 * Bundle of: axios-actions
 * Generated: 2018-12-17
 * Version: 4.0.1
 */

(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports) :
  typeof define === 'function' && define.amd ? define(['exports'], factory) :
  (factory((global.AxiosActions = {})));
}(this, (function (exports) { 'use strict';

  function isObject(value) {
      return typeof value === 'object' && !!value && !Array.isArray(value);
  }
  function isValue(value) {
      return typeof value !== 'undefined' && value !== null;
  }
  function getValue(obj, path) {
      var keys = path.split('.');
      while (obj && keys.length) {
          var key = keys.shift();
          obj = isObject(obj)
              ? obj[key]
              : undefined;
      }
      return obj;
  }
  /**
   * Flip the keys of an object
   *
   * @param   {object}  obj   A hash of key => value pairs
   */
  function flipKeys(obj) {
      return Object.keys(obj).reduce(function (output, key) {
          output[obj[key]] = key;
          return output;
      }, {});
  }
  /**
   * Re-keys an object using a map of oldKey => newKey pairs
   *
   * @param {object}            obj   The source object to re-key
   * @param {object|function}   map   A map of keys or function
   * @param {boolean}           flip  A boolean to flip the mapping
   * @returns {*}
   */
  function reKey(obj, map, flip) {
      if (map instanceof Function) {
          return map(obj, flip, reKey);
      }
      if (isObject(map)) {
          if (flip) {
              map = flipKeys(map);
          }
          return Object
              .keys(obj)
              .reduce(function (output, key) {
              output[map[key] || key] = obj[key];
              return output;
          }, {});
      }
      return obj;
  }

  var commonjsGlobal = typeof window !== 'undefined' ? window : typeof global !== 'undefined' ? global : typeof self !== 'undefined' ? self : {};

  function createCommonjsModule(fn, module) {
  	return module = { exports: {} }, fn(module, module.exports), module.exports;
  }

  var umd = createCommonjsModule(function (module, exports) {
  (function (global, factory) {
  	module.exports = factory();
  }(commonjsGlobal, (function () {
  var isMergeableObject = function isMergeableObject(value) {
  	return isNonNullObject(value)
  		&& !isSpecial(value)
  };

  function isNonNullObject(value) {
  	return !!value && typeof value === 'object'
  }

  function isSpecial(value) {
  	var stringValue = Object.prototype.toString.call(value);

  	return stringValue === '[object RegExp]'
  		|| stringValue === '[object Date]'
  		|| isReactElement(value)
  }

  // see https://github.com/facebook/react/blob/b5ac963fb791d1298e7f396236383bc955f916c1/src/isomorphic/classic/element/ReactElement.js#L21-L25
  var canUseSymbol = typeof Symbol === 'function' && Symbol.for;
  var REACT_ELEMENT_TYPE = canUseSymbol ? Symbol.for('react.element') : 0xeac7;

  function isReactElement(value) {
  	return value.$$typeof === REACT_ELEMENT_TYPE
  }

  function emptyTarget(val) {
  	return Array.isArray(val) ? [] : {}
  }

  function cloneUnlessOtherwiseSpecified(value, options) {
  	return (options.clone !== false && options.isMergeableObject(value))
  		? deepmerge(emptyTarget(value), value, options)
  		: value
  }

  function defaultArrayMerge(target, source, options) {
  	return target.concat(source).map(function(element) {
  		return cloneUnlessOtherwiseSpecified(element, options)
  	})
  }

  function mergeObject(target, source, options) {
  	var destination = {};
  	if (options.isMergeableObject(target)) {
  		Object.keys(target).forEach(function(key) {
  			destination[key] = cloneUnlessOtherwiseSpecified(target[key], options);
  		});
  	}
  	Object.keys(source).forEach(function(key) {
  		if (!options.isMergeableObject(source[key]) || !target[key]) {
  			destination[key] = cloneUnlessOtherwiseSpecified(source[key], options);
  		} else {
  			destination[key] = deepmerge(target[key], source[key], options);
  		}
  	});
  	return destination
  }

  function deepmerge(target, source, options) {
  	options = options || {};
  	options.arrayMerge = options.arrayMerge || defaultArrayMerge;
  	options.isMergeableObject = options.isMergeableObject || isMergeableObject;

  	var sourceIsArray = Array.isArray(source);
  	var targetIsArray = Array.isArray(target);
  	var sourceAndTargetTypesMatch = sourceIsArray === targetIsArray;

  	if (!sourceAndTargetTypesMatch) {
  		return cloneUnlessOtherwiseSpecified(source, options)
  	} else if (sourceIsArray) {
  		return options.arrayMerge(target, source, options)
  	} else {
  		return mergeObject(target, source, options)
  	}
  }

  deepmerge.all = function deepmergeAll(array, options) {
  	if (!Array.isArray(array)) {
  		throw new Error('first argument should be an array')
  	}

  	return array.reduce(function(prev, next) {
  		return deepmerge(prev, next, options)
  	}, {})
  };

  var deepmerge_1 = deepmerge;

  return deepmerge_1;

  })));
  });

  function makeRequest(config, method) {
      if ( method === void 0 ) method = 'get';

      // method / method + url passed
      if (typeof config === 'string') {
          var matches = config.trim().match(/^(?:(get|post|patch|put|delete|head)\s+)?(\S+)$/i);
          if (matches) {
              var m = matches[1];
              var url = matches[2];
              method = m ? m.toLowerCase() : method;
              return { method: method, url: url };
          }
      }
      // request config was passed
      else if (isObject(config) && config.url) {
          if (!config.method) {
              config.method = method;
          }
          return Object.assign({}, config);
      }
      throw new Error('Invalid request parameters');
  }
  function mergeOptions(config, options) {
      if ( options === void 0 ) options = {};

      return umd(config, options);
  }

  function replaceTokens(template, data, pattern) {
      if ( pattern === void 0 ) pattern = /[:{](\w+[.\w]+)}?/;

      // replace array
      if (Array.isArray(data)) {
          var d = [].concat( data );
          while (d.length) {
              template = template.replace(pattern, String(d.shift()));
          }
          return template;
      }
      // replace object
      var rx = new RegExp(pattern.source, 'g');
      return template.replace(rx, function (match, key) {
          var value = isObject(data)
              ? getValue(data, key)
              : data;
          return isValue(value)
              ? value
              : '';
      });
  }



  var index = /*#__PURE__*/Object.freeze({
    isObject: isObject,
    isValue: isValue,
    getValue: getValue,
    flipKeys: flipKeys,
    reKey: reKey,
    makeRequest: makeRequest,
    mergeOptions: mergeOptions,
    replaceTokens: replaceTokens
  });

  var Http = function Http(axios) {
      this.axios = axios;
      this.before = [];
      this.after = [];
      this.done = new Set();
      this.fail = new Set();
      this.queue = new Map;
      Object.freeze(this);
  };
  /**
   * Dispatch an axios request
   *
   * @param   instance  The ApiCore instance which should make the call
   * @param   configAn AxiosRequestConfig with all data for the call
   * @param   data  Any optional data to pass to the endpoints
   * @returns {Promise<any>}
   */
  Http.prototype.request = function request (instance, config, data) {
          var this$1 = this;
          if ( data === void 0 ) data = null;

      // reset
      instance.error = null;
      instance.loading = true;
      // variables
      var method = config.method;
          var url = config.url;
      method = method.toLowerCase();
      // sanity check
      if (typeof this.axios[method] !== 'function') {
          throw new Error(("No such HTTP method '" + method + "'"));
      }
      // variables
      data = this.before.reduce(function (data, fn) { return fn(data); }, data);
      url = replaceTokens(url, data);
      // setup object
      config = Object.assign({}, config);
      config.method = method;
      config.url = url;
      // data
      if (data) {
          if (method === 'get' && isObject(data) && !url.includes('?')) {
              config.params = data;
          }
          else {
              config.data = data;
          }
      }
      // loading
      var promise = this.axios.request(config);
      var key = Symbol((method + " " + url));
      this.queue.set(key, promise);
      var setLoaded = function (key) {
          this$1.queue.delete(key);
          instance.loading = this$1.queue.size > 0;
      };
      // call
      return promise
          .then(function (res) {
          setLoaded(key);
          this$1.after.forEach(function (fn) {
              var result = fn(res);
              if (typeof result !== 'undefined') {
                  res = result;
              }
          });
          this$1.done.forEach(function (fn) { return fn(res); });
          return res;
      })
          .catch(function (error) {
          setLoaded(key);
          instance.error = error;
          this$1.fail.forEach(function (fn) { return fn(error); });
          return Promise.reject(error);
      });
  };

  var Action = function Action(config, handler) {
      this.config = config;
      if (handler) {
          this.addHandler(handler);
      }
  };
  Action.prototype.addHandler = function addHandler (handler) {
      if (!this.handlers) {
          this.handlers = new Set();
      }
      this.handlers.add(handler);
  };
  Action.prototype.removeHandler = function removeHandler (handler) {
      this.handlers.delete(handler);
  };
  Action.prototype.exec = function exec (data, action) {
      if (this.handlers) {
          this.handlers.forEach(function (handler) { return handler(data, action); });
      }
  };

  var ActionMap = function ActionMap(actions) {
      var this$1 = this;
      if ( actions === void 0 ) actions = null;

      this.map = {};
      if (actions) {
          Object
              .keys(actions)
              .forEach(function (name) {
              this$1.add(name, actions[name]);
          });
      }
  };
  ActionMap.prototype.get = function get (name) {
      return this.map[name];
  };
  /**
   * Add an action
   *
   * @param   name  The name of the action to add
   * @param   configAn object conforming to the AxiosRequestConfig interface
   * @param   handler   An optional handler function to fire on a successful call
   */
  ActionMap.prototype.add = function add (name, config, handler) {
      this.map[name] = new Action(config, handler);
  };
  /**
   * Remove an action
   *
   * @param   name  The name of the action to delete
   */
  ActionMap.prototype.remove = function remove (name) {
      delete this.map[name];
  };
  /**
   * Add callbacks to named actions
   *
   * @param   name  The name or names of actions to bind to
   * @param   handler   The function to fire
   * @returns this
   */
  ActionMap.prototype.addHandler = function addHandler (name, handler) {
          var this$1 = this;

      var names = name.match(/\w+/g);
      names.forEach(function (name) {
          var action = this$1.get(name);
          if (action) {
              action.addHandler(handler);
          }
      });
      return this;
  };
  ActionMap.prototype.removeHandler = function removeHandler (name, handler) {
      var action = this.get(name);
      if (action) {
          action.removeHandler(handler);
      }
  };

  /**
   * Helper function to process single object or array
   *
   * @param   data
   * @param   callback
   * @returns
   */
  function process(data, callback) {
      if (Array.isArray(data)) {
          return data.map(function (obj) { return callback(obj); });
      }
      if (isObject(data)) {
          return callback(data);
      }
      return data;
  }
  /**
   * Default conversion function for resource plugin
   *
   * @param data
   * @returns {any}
   */
  function toJSON(data) {
      return isObject(data) && data.toJSON instanceof Function
          ? data.toJSON()
          : data;
  }
  /**
   * Helper function to get raw URLs from config or service
   *
   * @param instance
   * @param root
   */
  function getUrls(instance, root) {
      // type
      var isApiGroup = instance.actions instanceof ActionMap;
      // config
      var config = isApiGroup
          ? instance.actions.map
          : instance;
      // root path
      var rootPath = '';
      if (root) {
          if (!isApiGroup) {
              throw new Error('Root path can only be queried on ApiGroup instances');
          }
          rootPath = instance.http.axios.defaults.baseURL || '';
      }
      // build
      return Object
          .keys(config)
          .reduce(function (urls, key) {
          var value = config[key];
          var path = typeof value === 'string'
              ? value.replace(/^\w+\s+/, '')
              : isApiGroup
                  ? value.config.url
                  : value.url;
          urls[key] = rootPath + path;
          return urls;
      }, {});
  }

  var helpers = /*#__PURE__*/Object.freeze({
    process: process,
    toJSON: toJSON,
    getUrls: getUrls
  });

  /**
   * Converts axios data to and from models
   *
   * @param api
   * @param model
   * @param convert
   */
  function resource(api, model, convert) {
      if ( convert === void 0 ) convert = toJSON;

      api.http.before.push(function (data) { return process(data, function (data) { return convert(data); }); });
      api.http.after.push(function (res) { return process(res.data, function (data) { return new model(data); }); });
      return this;
  }
  /**
   * Remaps payload key names between client and server
   *
   * @param api
   * @param map
   */
  function remap(api, map) {
      api.http.before.push(function (data) {
          process(data, function (obj) { return reKey(obj, map, false); });
          return data;
      });
      api.http.after.push(function (res) {
          res.data = process(res.data, function (obj) { return reKey(obj, map, true); });
          return res;
      });
      return this;
  }
  /**
   * Returns response data rather than the response itself
   *
   * @param api
   */
  function data(api) {
      api.http.after.push(function (res) { return res.data; });
      return this;
  }

  var plugins = /*#__PURE__*/Object.freeze({
    resource: resource,
    remap: remap,
    data: data
  });

  /**
   * Api class
   *
   * Base class to manage calls to API
   */
  var ApiCore = function ApiCore(axios) {
      this.http = Object.freeze(new Http(axios));
      this.error = null;
      this.loading = false;
      Object.freeze(this.http);
  };
  /**
   * Use one of the built-in plugins
   *
   * @param   name  The name of the plugin
   * @param   paramsAny options the plugin needs
   * @returns {this}
   */
  ApiCore.prototype.use = function use (name) {
          var params = [], len = arguments.length - 1;
          while ( len-- > 0 ) params[ len ] = arguments[ len + 1 ];

      var plugin = plugins[name];
      if (!plugin) {
          throw new Error(("No such plugin \"" + name + "\""));
      }
      plugin.apply(void 0, [ this ].concat( params ));
      return this;
  };
  /**
   * Query the API using a request config object
   *
   * @param   configThe HTTP method to make the call
   * @param   data  Any optional data to pass to the endpoints
   * @returns
   */
  ApiCore.prototype.request = function request (config, data$$1) {
      return this.http.request(this, config, data$$1);
  };
  /**
   * Call the API via HTTP GET
   *
   * @param   url   The API URL to call
   * @param   data  Any optional data to pass to the endpoint
   * @param   options   An optional hash of options to merge into the Axios config
   * @returns
   */
  ApiCore.prototype.get = function get (url, data$$1, options) {
      return this.request(mergeOptions({ url: url, method: 'get' }, options), data$$1);
  };
  /**
   * Call the API via HTTP POST
   *
   * @param   url   The API URL to call
   * @param   data  Any optional data to pass to the endpoint
   * @param   options   An optional hash of options to merge into the Axios config
   * @returns
   */
  ApiCore.prototype.post = function post (url, data$$1, options) {
      return this.request(mergeOptions({ url: url, method: 'post' }, options), data$$1);
  };
  /**
   * Add a callback to fire when any call completes successfully
   *
   * @param   callback  The function to fire
   * @returns
   */
  ApiCore.prototype.done = function done (callback) {
      this.http.done.add(callback);
      return this;
  };
  /**
   * Add a callback to fire when any call fails to complete
   *
   * @param   callback  The function to fire
   * @returns
   */
  ApiCore.prototype.fail = function fail (callback) {
      this.http.fail.add(callback);
      return this;
  };

  /**
   * Group class
   *
   * Manages related URLs of an API
   */
  var ApiGroup = /*@__PURE__*/(function (ApiCore$$1) {
      function ApiGroup(axios, actions) {
          var this$1 = this;
          if ( actions === void 0 ) actions = null;

          ApiCore$$1.call(this, axios);
          this.actions = Object.freeze(new ActionMap());
          if (isObject(actions)) {
              Object
                  .keys(actions)
                  .forEach(function (action) {
                  this$1.add(action, actions[action]);
              });
          }
      }

      if ( ApiCore$$1 ) ApiGroup.__proto__ = ApiCore$$1;
      ApiGroup.prototype = Object.create( ApiCore$$1 && ApiCore$$1.prototype );
      ApiGroup.prototype.constructor = ApiGroup;
      ApiGroup.prototype.add = function add (action, config, callback) {
          var this$1 = this;

          config = makeRequest(config);
          this.actions.add(action, config, callback);
          if (!(action in this)) {
              this[action] = function (data, options) {
                  return this$1.call(action, data, options);
              };
          }
          return this;
      };
      /**
       * Call a specific action
       *
       * @param   name      The name of the action to execute
       * @param   data      An optional hash of data to send to the server
       * @param   options   An optional hash of options to merge into the Axios config
       * @returns {Promise<any>}
       */
      ApiGroup.prototype.call = function call (name, data, options) {
          var action = this.actions.get(name);
          if (!action) {
              throw new Error(("No such action \"" + action + "\""));
          }
          return this
              .request(mergeOptions(action.config, options), data)
              .then(function (res) {
              if (action.handlers) {
                  action.exec(res, name);
              }
              return Promise.resolve(res);
          });
      };
      /**
       * Add a callback to fire when any call completes successfully
       *
       * @param   name      The name or names of actions to bind to
       * @param   callback  The function to fire
       * @returns this
       */
      ApiGroup.prototype.when = function when (name, callback) {
          this.actions.addHandler(name, callback);
          return this;
      };

      return ApiGroup;
  }(ApiCore));

  /**
   * Endpoint class
   *
   * Manages CRUD and index calls for a specific endpoint
   */
  var ApiEndpoint = /*@__PURE__*/(function (ApiGroup$$1) {
      function ApiEndpoint(axios, config) {
          var this$1 = this;

          ApiGroup$$1.call(this, axios);
          // rest
          if (typeof config === 'string') {
              var methods = {
                  read: 'get',
                  index: 'get',
                  create: 'post',
                  update: 'patch',
                  delete: 'delete'
              };
              Object
                  .keys(methods)
                  .forEach(function (action) {
                  this$1.add(action, {
                      method: methods[action],
                      url: config
                  });
              });
          }
          // object
          else if (isObject(config)) {
              var methods$1 = {
                  read: 'get',
                  index: 'get',
                  create: 'post',
                  update: 'post',
                  delete: 'post'
              };
              Object
                  .keys(config)
                  .map(function (action) {
                  var request = makeRequest(config[action], methods$1[action]);
                  this$1.add(action, request);
              });
          }
      }

      if ( ApiGroup$$1 ) ApiEndpoint.__proto__ = ApiGroup$$1;
      ApiEndpoint.prototype = Object.create( ApiGroup$$1 && ApiGroup$$1.prototype );
      ApiEndpoint.prototype.constructor = ApiEndpoint;
      /**
       * Load the resource index
       * @param   data
       */
      ApiEndpoint.prototype.index = function index (data) {
          return this.call('index', data);
      };
      /**
       * Create a new resource
       * @param   data
       */
      ApiEndpoint.prototype.create = function create (data) {
          if (!isObject(data)) {
              throw new Error('Missing data parameter');
          }
          return this.call('create', data);
      };
      /**
       * Read a single resource
       * @param   id
       */
      ApiEndpoint.prototype.read = function read (id) {
          if (typeof id === 'undefined') {
              throw new Error('Missing id parameter');
          }
          return this.call('read', id);
      };
      /**
       * Update the resource
       * @param   data
       */
      ApiEndpoint.prototype.update = function update (data) {
          if (!isObject(data)) {
              throw new Error('Missing data parameter');
          }
          return this.call('update', data);
      };
      /**
       * Delete the resource
       * @param   id
       */
      ApiEndpoint.prototype.delete = function delete$1 (id) {
          if (typeof id === 'undefined') {
              throw new Error('Missing id parameter');
          }
          return this.call('delete', id);
      };

      return ApiEndpoint;
  }(ApiGroup));

  /**
   * Resource class
   *
   * Example class showing how Endpoint can be extended to be completely self-contained using plugins and handlers
   *
   * Featuring:
   *
   * - CRUD, index and search
   * - stores data on the class
   * - optional automatic model conversion
   * - optional auto-reload on create, update and delete
   */
  var ApiResource = /*@__PURE__*/(function (ApiEndpoint$$1) {
      function ApiResource(axios, config, model, reload) {
          var this$1 = this;
          if ( reload === void 0 ) reload = false;

          ApiEndpoint$$1.call(this, axios, config);
          // data
          this.items = [];
          this.item = null;
          // add search
          var search = this.actions.get('search');
          if (!search) {
              var index = this.actions.get('index');
              this.add('search', index.config);
          }
          // always save data
          this
              .when('index search', function (items) {
              this$1.items = items;
          })
              .when('create read update delete', function (item) {
              this$1.item = item;
          });
          // optionally reload when updated
          if (reload) {
              this.when('create update delete', function (data) { return this$1.index(); });
          }
          // optionally use models
          if (model) {
              this.use('resource', model);
          }
          // resolve data
          this.use('data');
      }

      if ( ApiEndpoint$$1 ) ApiResource.__proto__ = ApiEndpoint$$1;
      ApiResource.prototype = Object.create( ApiEndpoint$$1 && ApiEndpoint$$1.prototype );
      ApiResource.prototype.constructor = ApiResource;
      ApiResource.prototype.search = function search (data) {
          return this.call('search', data);
      };

      return ApiResource;
  }(ApiEndpoint));

  exports.default = ApiCore;
  exports.ApiCore = ApiCore;
  exports.ApiGroup = ApiGroup;
  exports.ApiEndpoint = ApiEndpoint;
  exports.ApiResource = ApiResource;
  exports.plugins = plugins;
  exports.helpers = helpers;
  exports.utils = index;

  Object.defineProperty(exports, '__esModule', { value: true });

})));
